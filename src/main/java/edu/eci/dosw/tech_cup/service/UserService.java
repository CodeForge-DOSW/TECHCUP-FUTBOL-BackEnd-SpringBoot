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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * JPA-backed implementation of the user service contract.
 *
 * <p>This service centralizes validation, persistence, and authentication rules
 * for platform users. It coordinates entity-model conversions through
 * {@link UserMapper} and delegates database access to {@link UserRepository}.</p>
 */
@Service
public class UserService implements IUserService, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /**
     * Repository used to persist and query user records.
     */
    private final UserRepository userRepository;

    /**
     * Mapper used to convert between persistence entities and service models.
     */
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user after validating the request data and email uniqueness.
     *
     * <p>If the user type is missing, the service defaults it to {@code student}.
     * The resulting persisted account is always marked as active.</p>
     *
     * @param user user data to validate and persist
     * @return the persisted user mapped back to the service model
     * @throws RuntimeException if the payload is null, the email is invalid or duplicated,
     * or the user type is not allowed
     */
    @Override
    @Transactional
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


        if (user.getUserType() == null || user.getUserType().trim().isEmpty()) {
            user.setUserType("student");
        }
        String type = user.getUserType().trim().toLowerCase();
        if (!type.equals("student") && !type.equals("professor") && !type.equals("graduate")
                && !type.equals("administrative") && !type.equals("family")) {
            throw new RuntimeException(
                    "Invalid user type. Allowed: student, professor, graduate, administrative, family");
        }
        user.setUserType(type);


        String email = user.getEmail().trim().toLowerCase();
        switch (type) {
            case "student":
            case "graduate":
                if (!email.endsWith("@mail.escuelaing.edu.co")) {
                    throw new RuntimeException(
                            "Students and graduates must use institutional email (@mail.escuelaing.edu.co)");
                }
                break;
            case "professor":
            case "administrative":
                if (!email.endsWith("@escuelaing.edu.co")) {
                    throw new RuntimeException(
                            "Staff must use institutional email (@escuelaing.edu.co)");
                }
                break;
            case "family":
                if (!email.endsWith("@gmail.com")) {
                    throw new RuntimeException(
                            "Family members must use a Gmail account (@gmail.com)");
                }
                break;
        }

        UserEntity entity = userMapper.toEntity(user);
        entity.setPasswordUser(passwordEncoder.encode(user.getPassword()));
        entity.setStatus(true);

        UserEntity saved = userRepository.save(entity);
        log.info("User created with id: {}", saved.getUserId());
        return userMapper.toModel(saved);
    }

    /**
     * Retrieves a user by its identifier.
     *
     * @param id unique user identifier
     * @return the mapped user model
     * @throws RuntimeException if no user exists with the given identifier
     */
    @Override
    public PlayerModel getUser(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toModel(entity);
    }

    /**
     * Retrieves every stored user.
     *
     * @return a list of mapped user models, which may be empty
     */
    @Override
    public List<UserRoleModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Updates selected fields of an existing user.
     *
     * <p>Only non-null fields from the incoming payload are applied. When the
     * email is updated, the service validates format and uniqueness first.</p>
     *
     * @param id identifier of the user to update
     * @param updatedUser payload containing the fields to modify
     * @return the updated user mapped back to the service model
     * @throws RuntimeException if the payload is null, the user does not exist,
     * or the email is invalid or already in use
     */
    @Override
    @Transactional
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

    /**
     * Performs a logical delete by marking the user as inactive.
     *
     * @param id identifier of the user to deactivate
     * @throws RuntimeException if the user does not exist
     */
    @Override
    @Transactional
    public void deactivateUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(false);
        userRepository.save(user);
        log.info("User {} deactivated", id);
    }

    /**
     * Authenticates a user by validating email, password, and active status.
     *
     * @param email user email address
     * @param password raw password value to verify
     * @throws RuntimeException if input data is missing, credentials are invalid,
     * or the account is inactive
     */
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
