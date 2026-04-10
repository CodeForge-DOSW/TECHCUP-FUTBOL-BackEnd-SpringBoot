package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.exception.NotFoundException;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.PlayerModel;
import edu.eci.dosw.tech_cup.model.UserRoleModel;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de usuarios con persistencia JPA.
 *
 * <p>Reemplaza la lista en memoria del laboratorio anterior por llamadas
 * reales al {@link UserRepository}. El flujo de cada operación es:</p>
 * <pre>
 *   Controller → Service → Mapper.toEntity() → Repository → Mapper.toModel() → Controller
 * </pre>
 */
@Service
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public PlayerModel createUser(PlayerModel user) {
        log.debug("Creating user with email: {}", user != null ? user.getEmail() : "null");

        if (user == null) {
            throw new RuntimeException("User cannot be null");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("Invalid email format");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setRole("PLAYER");

        UserEntity entity = userMapper.toEntity(user);
        entity.setStatus(true);

        UserEntity saved = userRepository.save(entity);
        log.info("User created with id: {}", saved.getUserId());
        return userMapper.toModel(saved);
    }

    @Override
    public PlayerModel getUser(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toModel(entity);
    }

    @Override
    public List<UserRoleModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerModel updateUser(Long id, PlayerModel updatedUser) {
        if (updatedUser == null) {
            throw new RuntimeException("Update data cannot be null");
        }

        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (updatedUser.getEmail() != null) {
            if (updatedUser.getEmail().trim().isEmpty()) {
                throw new RuntimeException("Email cannot be empty");
            }
            if (!updatedUser.getEmail().contains("@")) {
                throw new RuntimeException("Invalid email format");
            }
            boolean emailTaken = userRepository.findByEmail(updatedUser.getEmail())
                    .filter(u -> !u.getUserId().equals(id))
                    .isPresent();
            if (emailTaken) {
                throw new RuntimeException("Email already exists");
            }
            existing.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getFirstName() != null) {
            existing.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            existing.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getGender() != null) {
            existing.setGender(updatedUser.getGender());
        }

        UserEntity saved = userRepository.save(existing);
        return userMapper.toModel(saved);
    }

    @Override
    public void deactivateUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setStatus(false);
        userRepository.save(user);
        log.info("User {} deactivated", id);
    }

    @Override
    public void authenticate(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPasswordUser().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }
        if (!Boolean.TRUE.equals(user.getStatus())) {
            throw new RuntimeException("User account is inactive");
        }

        log.info("User {} authenticated successfully", email);
    }

    @Override
    public void assignRole(Long targetUserId, String newRole, Long adminUserId) {
        UserEntity admin = userRepository.findById(adminUserId)
                .orElseThrow(() -> new NotFoundException("Admin user not found"));

        PlayerModel adminModel = userMapper.toModel(admin);
        if (!"ADMIN".equalsIgnoreCase(adminModel.getRole())) {
            throw new RuntimeException("Only administrators can assign roles");
        }

        UserEntity target = userRepository.findById(targetUserId)
                .orElseThrow(() -> new NotFoundException("Target user not found"));

        PlayerModel targetModel = userMapper.toModel(target);
        targetModel.setRole(newRole.toUpperCase());
        userRepository.save(userMapper.toEntity(targetModel));
        log.info("Role {} assigned to user {} by admin {}", newRole, targetUserId, adminUserId);
    }
}