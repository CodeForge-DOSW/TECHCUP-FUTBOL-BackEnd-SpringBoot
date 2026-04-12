package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.PlayerModel;
import edu.eci.dosw.tech_cup.model.UserRoleModel;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
public class UserService implements IUserService, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
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
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("Invalid email format");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity entity = userMapper.toEntity(user);
        entity.setPasswordUser(passwordEncoder.encode(user.getPassword()));
        entity.setStatus(true);

        UserEntity saved = userRepository.save(entity);
        log.info("User created with id: {}", saved.getUserId());
        return userMapper.toModel(saved);
    }

    @Override
    public PlayerModel getUser(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
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
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updatedUser.getEmail() != null) {
            if (updatedUser.getEmail().trim().isEmpty()) {
                throw new RuntimeException("Email cannot be empty");
            }
            if (!updatedUser.getEmail().contains("@")) {
                throw new RuntimeException("Invalid email format");
            }
            boolean emailTaken = Optional.ofNullable(userRepository.findByEmail(updatedUser.getEmail()))
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
                .orElseThrow(() -> new RuntimeException("User not found"));
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

        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!matchesPassword(password, user.getPasswordUser())) {
            throw new RuntimeException("Invalid credentials");
        }
        if (!Boolean.TRUE.equals(user.getStatus())) {
            throw new RuntimeException("User account is inactive");
        }

        log.info("User {} authenticated successfully", email);
    }

    /**
     * a. Objetivo de loadUserByUsername: adaptar nuestro criterio de login (email)
     * al contrato de Spring Security, que siempre solicita un "username" para cargar
     * el usuario que va a autenticarse.
     * b. UserDetails representa el usuario en el contexto de seguridad: contiene
     * username, password y autoridades para que Spring valide credenciales y permisos.
     * c. SimpleGrantedAuthority representa una autoridad/rol concreto (por ejemplo,
     * ROLE_USER) que Spring Security usa para decisiones de autorización.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        String storedPassword = user.getPasswordUser();
        if (storedPassword == null || storedPassword.isBlank()) {
            throw new UsernameNotFoundException("User has no valid password: " + email);
        }

        String passwordForSecurity = isBcryptHash(storedPassword)
                ? storedPassword
                : passwordEncoder.encode(storedPassword);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                passwordForSecurity,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    private boolean matchesPassword(String rawPassword, String storedPassword) {
        if (storedPassword == null || storedPassword.isBlank()) {
            return false;
        }
        if (isBcryptHash(storedPassword)) {
            return passwordEncoder.matches(rawPassword, storedPassword);
        }
        return rawPassword.equals(storedPassword);
    }

    private boolean isBcryptHash(String value) {
        return value.startsWith("$2a$") || value.startsWith("$2b$") || value.startsWith("$2y$");
    }
}