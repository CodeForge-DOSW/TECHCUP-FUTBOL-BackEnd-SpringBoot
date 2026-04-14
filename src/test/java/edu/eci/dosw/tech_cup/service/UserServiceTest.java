package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.PlayerModel;
import edu.eci.dosw.tech_cup.model.UserRoleModel;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link UserService}.
 *
 * <p>This test suite validates user creation, retrieval, update, and logical
 * deactivation behavior by using mocked repository and mapper dependencies.</p>
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    /**
     * Builds a user entity for mocked repository responses.
     *
     * @param id persisted user identifier
     * @param email user email
     * @param name user display name
     * @param active whether the user is active
     * @return a user entity configured for tests
     */
    private UserEntity entityWith(Long id, String email, String name, boolean active) {
        UserEntity e = new UserEntity();
        e.setUserId(id);
        e.setEmail(email);
        e.setFirstName(name);
        e.setStatus(active);
        return e;
    }

    /**
     * Builds a player model for mocked mapper responses.
     *
     * @param id user identifier
     * @param email user email
     * @param name user display name
     * @param active whether the user is active
     * @return a player model configured for assertions
     */
    private PlayerModel modelWith(Long id, String email, String name, boolean active) {
        PlayerModel m = new PlayerModel();
        m.setId(id);
        m.setEmail(email);
        m.setName(name);
        m.setStatus(active);
        return m;
    }

    /**
     * Verifies that a user is created successfully when valid data is provided.
     */
    @DisplayName("Should create a user successfully with valid data")
    @Test
    void shouldCreateUser() {
        PlayerModel input = new PlayerModel();
        input.setEmail("juan@mail.escuelaing.edu.co");
        input.setPassword("123456");
        input.setName("Juan");

        UserEntity savedEntity = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        PlayerModel expectedModel = modelWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);

        when(userRepository.existsByEmail("juan@mail.escuelaing.edu.co")).thenReturn(false);
        when(passwordEncoder.encode("123456")).thenReturn("$2a$10$abcdefghijklmnopqrstuv12345678901234567890123456789012");
        when(userMapper.toEntity(input)).thenReturn(savedEntity);
        when(userRepository.save(savedEntity)).thenReturn(savedEntity);
        when(userMapper.toModel(savedEntity)).thenReturn(expectedModel);

        PlayerModel result = userService.createUser(input);

        assertNotNull(result.getId());
        assertEquals("Juan", result.getName());
        assertTrue(result.isActive());
    }

    /**
     * Verifies that creation fails when the user payload is null.
     */
    @DisplayName("Should throw exception when creating user with null")
    @Test
    void shouldFailCreateWhenUserIsNull() {
        assertThrows(RuntimeException.class, () -> userService.createUser(null));
        verifyNoInteractions(userRepository, userMapper);
    }

    /**
     * Verifies that creation fails when the email is missing.
     */
    @DisplayName("Should throw exception when email is null")
    @Test
    void shouldFailCreateWhenEmailIsNull() {
        PlayerModel user = new PlayerModel();
        user.setName("Juan");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
        verifyNoInteractions(userRepository, userMapper);
    }

    /**
     * Verifies that creation fails when the email is blank.
     */
    @DisplayName("Should throw exception when email is blank")
    @Test
    void shouldFailCreateWhenEmailIsBlank() {
        PlayerModel user = new PlayerModel();
        user.setEmail("   ");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
        verifyNoInteractions(userRepository, userMapper);
    }

    /**
     * Verifies that creation fails when another user already has the same email.
     */
    @DisplayName("Should throw exception when email already exists")
    @Test
    void shouldFailCreateWhenEmailExists() {
        PlayerModel user = new PlayerModel();
        user.setEmail("test@mail.com");
        user.setPassword("123456");

        when(userRepository.existsByEmail("test@mail.escuelaing.edu.co")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
        verify(userRepository, never()).save(any());
    }

    /**
     * Verifies that a user can be retrieved by identifier.
     */
    @DisplayName("Should return user by id")
    @Test
    void shouldGetUserById() {
        UserEntity entity = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        PlayerModel model = modelWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userMapper.toModel(entity)).thenReturn(model);

        UserRoleModel found = userService.getUser(1L);

        assertEquals("Juan", found.getName());
        assertEquals(1L, found.getId());
    }

    /**
     * Verifies that retrieval fails when the requested user does not exist.
     */
    @DisplayName("Should throw exception when user not found")
    @Test
    void shouldFailGetUser() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUser(999L));
    }

    /**
     * Verifies that the service returns all stored users.
     */
    @DisplayName("Should return all users")
    @Test
    void shouldGetAllUsers() {
        UserEntity e1 = entityWith(1L, "a@mail.escuelaing.edu.co", "Alice", true);
        UserEntity e2 = entityWith(2L, "b@mail.escuelaing.edu.co", "Bob", true);
        PlayerModel m1 = modelWith(1L, "a@mail.escuelaing.edu.co", "Alice", true);
        PlayerModel m2 = modelWith(2L, "b@mail.escuelaing.edu.co", "Bob", true);

        when(userRepository.findAll()).thenReturn(List.of(e1, e2));
        when(userMapper.toModel(e1)).thenReturn(m1);
        when(userMapper.toModel(e2)).thenReturn(m2);

        List<UserRoleModel> users = userService.getAllUsers();

        assertEquals(2, users.size());
    }

    /**
     * Verifies that the service returns an empty list when no users exist.
     */
    @DisplayName("Should return empty list when no users")
    @Test
    void shouldReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(List.of());

        assertTrue(userService.getAllUsers().isEmpty());
    }

    /**
     * Verifies that updating a user persists the modified data.
     */
    @DisplayName("Should update user name")
    @Test
    void shouldUpdateUser() {
        UserEntity existing = entityWith(1L, "juan@mail.escuelaing.edu.co", "Old", true);
        UserEntity savedEntity = entityWith(1L, "juan@mail.escuelaing.edu.co", "New", true);
        PlayerModel resultModel = modelWith(1L, "juan@mail.escuelaing.edu.co", "New", true);

        PlayerModel updatedPayload = new PlayerModel();
        updatedPayload.setName("New");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(existing)).thenReturn(savedEntity);
        when(userMapper.toModel(savedEntity)).thenReturn(resultModel);

        UserRoleModel result = userService.updateUser(1L, updatedPayload);

        assertEquals("New", result.getName());
    }

    /**
     * Verifies that updating fails when the target user does not exist.
     */
    @DisplayName("Should throw exception when updating non-existing user")
    @Test
    void shouldFailUpdateUser() {
        PlayerModel updatedPayload = new PlayerModel();
        updatedPayload.setName("Test");

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userService.updateUser(999L, updatedPayload));
    }

    /**
     * Verifies that updating fails when the request payload is null.
     */
    @DisplayName("Should throw exception when updating with null payload")
    @Test
    void shouldFailUpdateWithNull() {
        assertThrows(RuntimeException.class,
                () -> userService.updateUser(1L, null));
        verifyNoInteractions(userRepository, userMapper);
    }

    /**
     * Verifies that updating a user does not change its identifier.
     */
    @DisplayName("Should keep same id after update")
    @Test
    void shouldKeepSameId() {
        UserEntity existing = entityWith(1L, "test@mail.escuelaing.edu.co", "Old", true);
        UserEntity savedEntity = entityWith(1L, "test@mail.escuelaing.edu.co", "Updated", true);
        PlayerModel resultModel = modelWith(1L, "test@mail.escuelaing.edu.co", "Updated", true);

        PlayerModel updatedPayload = new PlayerModel();
        updatedPayload.setName("Updated");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(existing)).thenReturn(savedEntity);
        when(userMapper.toModel(savedEntity)).thenReturn(resultModel);

        UserRoleModel result = userService.updateUser(1L, updatedPayload);

        assertEquals(1L, result.getId());
    }

    /**
     * Verifies that deactivating a user sets its status to inactive and saves the change.
     */
    @DisplayName("Should deactivate user")
    @Test
    void shouldDeactivateUser() {
        UserEntity entity = entityWith(1L, "test@mail.com", "Test", true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userRepository.save(entity)).thenReturn(entity);

        userService.deactivateUser(1L);

        verify(userRepository).save(entity);
        assertFalse(entity.getStatus());
    }

    /**
     * Verifies that a deactivated user remains stored and is returned as inactive.
     */
    @DisplayName("Should keep user after deactivation")
    @Test
    void shouldKeepUserAfterDeactivate() {
        UserEntity entity = entityWith(1L, "test@mail.escuelaing.edu.co", "Test", true);
        PlayerModel deactivatedModel = modelWith(1L, "test@mail.escuelaing.edu.co", "Test", false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userRepository.save(entity)).thenReturn(entity);
        when(userRepository.findAll()).thenReturn(List.of(entity));
        when(userMapper.toModel(entity)).thenReturn(deactivatedModel);

        userService.deactivateUser(1L);

        List<UserRoleModel> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertFalse(users.get(0).isActive());
    }

    // =========================================================
    // createUser – email domain and type validations
    // =========================================================

    @DisplayName("Should create professor user with correct institutional email")
    @Test
    void shouldCreateProfessorWithCorrectEmail() {
        PlayerModel input = new PlayerModel();
        input.setEmail("profe@escuelaing.edu.co");
        input.setPassword("pass123");
        input.setUserType("professor");

        UserEntity saved = entityWith(1L, "profe@escuelaing.edu.co", "Profe", true);
        PlayerModel expected = modelWith(1L, "profe@escuelaing.edu.co", "Profe", true);

        when(userRepository.existsByEmail("profe@escuelaing.edu.co")).thenReturn(false);
        when(passwordEncoder.encode("pass123")).thenReturn("$2a$10$hashed");
        when(userMapper.toEntity(input)).thenReturn(saved);
        when(userRepository.save(saved)).thenReturn(saved);
        when(userMapper.toModel(saved)).thenReturn(expected);

        PlayerModel result = userService.createUser(input);

        assertNotNull(result.getId());
    }

    @DisplayName("Should create administrative user with correct institutional email")
    @Test
    void shouldCreateAdministrativeWithCorrectEmail() {
        PlayerModel input = new PlayerModel();
        input.setEmail("admin@escuelaing.edu.co");
        input.setPassword("pass123");
        input.setUserType("administrative");

        UserEntity saved = entityWith(1L, "admin@escuelaing.edu.co", "Admin", true);
        PlayerModel expected = modelWith(1L, "admin@escuelaing.edu.co", "Admin", true);

        when(userRepository.existsByEmail("admin@escuelaing.edu.co")).thenReturn(false);
        when(passwordEncoder.encode("pass123")).thenReturn("$2a$10$hashed");
        when(userMapper.toEntity(input)).thenReturn(saved);
        when(userRepository.save(saved)).thenReturn(saved);
        when(userMapper.toModel(saved)).thenReturn(expected);

        PlayerModel result = userService.createUser(input);

        assertNotNull(result.getId());
    }

    @DisplayName("Should create family user with Gmail account")
    @Test
    void shouldCreateFamilyWithGmailEmail() {
        PlayerModel input = new PlayerModel();
        input.setEmail("familiar@gmail.com");
        input.setPassword("pass123");
        input.setUserType("family");

        UserEntity saved = entityWith(1L, "familiar@gmail.com", "Fam", true);
        PlayerModel expected = modelWith(1L, "familiar@gmail.com", "Fam", true);

        when(userRepository.existsByEmail("familiar@gmail.com")).thenReturn(false);
        when(passwordEncoder.encode("pass123")).thenReturn("$2a$10$hashed");
        when(userMapper.toEntity(input)).thenReturn(saved);
        when(userRepository.save(saved)).thenReturn(saved);
        when(userMapper.toModel(saved)).thenReturn(expected);

        PlayerModel result = userService.createUser(input);

        assertNotNull(result.getId());
    }

    @DisplayName("Should create graduate user with institutional mail email")
    @Test
    void shouldCreateGraduateWithCorrectEmail() {
        PlayerModel input = new PlayerModel();
        input.setEmail("egresado@mail.escuelaing.edu.co");
        input.setPassword("pass123");
        input.setUserType("graduate");

        UserEntity saved = entityWith(1L, "egresado@mail.escuelaing.edu.co", "Egresado", true);
        PlayerModel expected = modelWith(1L, "egresado@mail.escuelaing.edu.co", "Egresado", true);

        when(userRepository.existsByEmail("egresado@mail.escuelaing.edu.co")).thenReturn(false);
        when(passwordEncoder.encode("pass123")).thenReturn("$2a$10$hashed");
        when(userMapper.toEntity(input)).thenReturn(saved);
        when(userRepository.save(saved)).thenReturn(saved);
        when(userMapper.toModel(saved)).thenReturn(expected);

        assertNotNull(userService.createUser(input).getId());
    }

    @DisplayName("Should default user type to student when not provided")
    @Test
    void shouldDefaultToStudentTypeWhenTypeIsNull() {
        PlayerModel input = new PlayerModel();
        input.setEmail("juan@mail.escuelaing.edu.co");
        input.setPassword("pass");
        // userType intentionally null

        UserEntity saved = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        PlayerModel expected = modelWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);

        when(userRepository.existsByEmail("juan@mail.escuelaing.edu.co")).thenReturn(false);
        when(passwordEncoder.encode("pass")).thenReturn("$2a$10$hashed");
        when(userMapper.toEntity(input)).thenReturn(saved);
        when(userRepository.save(saved)).thenReturn(saved);
        when(userMapper.toModel(saved)).thenReturn(expected);

        assertNotNull(userService.createUser(input).getId());
        assertEquals("student", input.getUserType());
    }

    @DisplayName("Should throw exception when password is null")
    @Test
    void shouldFailCreateWhenPasswordIsNull() {
        PlayerModel user = new PlayerModel();
        user.setEmail("test@mail.escuelaing.edu.co");
        // password intentionally null

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    @DisplayName("Should throw exception when email has no @ symbol")
    @Test
    void shouldFailCreateWithInvalidEmailFormat() {
        PlayerModel user = new PlayerModel();
        user.setEmail("invalidemail");
        user.setPassword("123456");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    @DisplayName("Should throw exception when user type is invalid")
    @Test
    void shouldFailCreateWithInvalidUserType() {
        PlayerModel user = new PlayerModel();
        user.setEmail("test@mail.escuelaing.edu.co");
        user.setPassword("123456");
        user.setUserType("hacker");

        when(userRepository.existsByEmail("test@mail.escuelaing.edu.co")).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.createUser(user));
        assertTrue(ex.getMessage().contains("Invalid user type"));
    }

    @DisplayName("Should throw exception when student uses wrong email domain")
    @Test
    void shouldFailCreateStudentWithWrongEmailDomain() {
        PlayerModel user = new PlayerModel();
        user.setEmail("student@gmail.com");
        user.setPassword("123456");
        user.setUserType("student");

        when(userRepository.existsByEmail("student@gmail.com")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    @DisplayName("Should throw exception when professor uses wrong email domain")
    @Test
    void shouldFailCreateProfessorWithWrongEmailDomain() {
        PlayerModel user = new PlayerModel();
        user.setEmail("profe@mail.escuelaing.edu.co");
        user.setPassword("123456");
        user.setUserType("professor");

        when(userRepository.existsByEmail("profe@mail.escuelaing.edu.co")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    @DisplayName("Should throw exception when family member uses non-Gmail email")
    @Test
    void shouldFailCreateFamilyWithNonGmailEmail() {
        PlayerModel user = new PlayerModel();
        user.setEmail("fam@hotmail.com");
        user.setPassword("123456");
        user.setUserType("family");

        when(userRepository.existsByEmail("fam@hotmail.com")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    // =========================================================
    // updateUser – email branches
    // =========================================================

    @DisplayName("Should update user email successfully")
    @Test
    void shouldUpdateUserEmail() {
        UserEntity existing = entityWith(1L, "old@mail.escuelaing.edu.co", "Juan", true);
        UserEntity saved    = entityWith(1L, "new@mail.escuelaing.edu.co", "Juan", true);
        PlayerModel result  = modelWith(1L, "new@mail.escuelaing.edu.co", "Juan", true);

        PlayerModel payload = new PlayerModel();
        payload.setEmail("new@mail.escuelaing.edu.co");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.findByEmail("new@mail.escuelaing.edu.co")).thenReturn(null);
        when(userRepository.save(existing)).thenReturn(saved);
        when(userMapper.toModel(saved)).thenReturn(result);

        UserRoleModel updated = userService.updateUser(1L, payload);

        assertEquals("new@mail.escuelaing.edu.co", updated.getEmail());
    }

    @DisplayName("Should throw exception when updating email to blank string")
    @Test
    void shouldFailUpdateWithBlankEmail() {
        UserEntity existing = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);

        PlayerModel payload = new PlayerModel();
        payload.setEmail("   ");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));

        assertThrows(RuntimeException.class, () -> userService.updateUser(1L, payload));
    }

    @DisplayName("Should throw exception when updating email with invalid format")
    @Test
    void shouldFailUpdateWithInvalidEmailFormat() {
        UserEntity existing = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);

        PlayerModel payload = new PlayerModel();
        payload.setEmail("notanemail");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));

        assertThrows(RuntimeException.class, () -> userService.updateUser(1L, payload));
    }

    @DisplayName("Should throw exception when new email is already used by another user")
    @Test
    void shouldFailUpdateWhenEmailTakenByAnotherUser() {
        UserEntity existing = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        UserEntity other    = entityWith(2L, "taken@mail.escuelaing.edu.co", "Other", true);

        PlayerModel payload = new PlayerModel();
        payload.setEmail("taken@mail.escuelaing.edu.co");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.findByEmail("taken@mail.escuelaing.edu.co")).thenReturn(other);

        assertThrows(RuntimeException.class, () -> userService.updateUser(1L, payload));
    }

    // =========================================================
    // deactivateUser – error path
    // =========================================================

    @DisplayName("Should throw exception when deactivating a non-existing user")
    @Test
    void shouldFailDeactivateUserNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.deactivateUser(999L));
    }

    // =========================================================
    // authenticate
    // =========================================================

    @DisplayName("Should authenticate user successfully with plain password")
    @Test
    void shouldAuthenticateSuccessfully() {
        UserEntity user = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        user.setPasswordUser("secret");
        user.setStatus(true);

        when(userRepository.findByEmail("juan@mail.escuelaing.edu.co")).thenReturn(user);

        assertDoesNotThrow(() ->
                userService.authenticate("juan@mail.escuelaing.edu.co", "secret"));
    }

    @DisplayName("Should authenticate user successfully with BCrypt stored password")
    @Test
    void shouldAuthenticateSuccessfullyWithBcrypt() {
        String bcryptHash = "$2a$10$abcdefghijklmnopqrstuuABCDEFGHIJKLMNOPQRSTUVWXYZ01234";
        UserEntity user = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        user.setPasswordUser(bcryptHash);
        user.setStatus(true);

        when(userRepository.findByEmail("juan@mail.escuelaing.edu.co")).thenReturn(user);
        when(passwordEncoder.matches("secret", bcryptHash)).thenReturn(true);

        assertDoesNotThrow(() ->
                userService.authenticate("juan@mail.escuelaing.edu.co", "secret"));
    }

    @DisplayName("Should throw exception when authenticate email is null")
    @Test
    void shouldFailAuthWhenEmailIsNull() {
        assertThrows(RuntimeException.class,
                () -> userService.authenticate(null, "pass123"));
    }

    @DisplayName("Should throw exception when authenticate email is blank")
    @Test
    void shouldFailAuthWhenEmailIsBlank() {
        assertThrows(RuntimeException.class,
                () -> userService.authenticate("  ", "pass123"));
    }

    @DisplayName("Should throw exception when authenticate password is null")
    @Test
    void shouldFailAuthWhenPasswordIsNull() {
        assertThrows(RuntimeException.class,
                () -> userService.authenticate("user@mail.escuelaing.edu.co", null));
    }

    @DisplayName("Should throw exception when authenticate password is blank")
    @Test
    void shouldFailAuthWhenPasswordIsBlank() {
        assertThrows(RuntimeException.class,
                () -> userService.authenticate("user@mail.escuelaing.edu.co", "  "));
    }

    @DisplayName("Should throw exception when user is not found during authentication")
    @Test
    void shouldFailAuthWhenUserNotFound() {
        when(userRepository.findByEmail("unknown@mail.escuelaing.edu.co")).thenReturn(null);

        assertThrows(RuntimeException.class,
                () -> userService.authenticate("unknown@mail.escuelaing.edu.co", "pass"));
    }

    @DisplayName("Should throw exception when password does not match during authentication")
    @Test
    void shouldFailAuthWhenPasswordWrong() {
        UserEntity user = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        user.setPasswordUser("correctPass");
        user.setStatus(true);

        when(userRepository.findByEmail("juan@mail.escuelaing.edu.co")).thenReturn(user);

        assertThrows(RuntimeException.class,
                () -> userService.authenticate("juan@mail.escuelaing.edu.co", "wrongPass"));
    }

    @DisplayName("Should throw exception when user account is inactive during authentication")
    @Test
    void shouldFailAuthWhenUserIsInactive() {
        UserEntity user = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", false);
        user.setPasswordUser("secret");
        user.setStatus(false);

        when(userRepository.findByEmail("juan@mail.escuelaing.edu.co")).thenReturn(user);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.authenticate("juan@mail.escuelaing.edu.co", "secret"));
        assertTrue(ex.getMessage().contains("inactive"));
    }

    // =========================================================
    // loadUserByUsername
    // =========================================================

    @DisplayName("Should load UserDetails successfully for an existing user")
    @Test
    void shouldLoadUserByUsernameSuccessfully() {
        UserEntity user = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        user.setPasswordUser("plainPass");

        when(userRepository.findByEmail("juan@mail.escuelaing.edu.co")).thenReturn(user);
        when(passwordEncoder.encode("plainPass")).thenReturn("$2a$10$encoded");

        var details = userService.loadUserByUsername("juan@mail.escuelaing.edu.co");

        assertNotNull(details);
        assertEquals("juan@mail.escuelaing.edu.co", details.getUsername());
    }

    @DisplayName("Should load UserDetails with BCrypt password without re-encoding")
    @Test
    void shouldLoadUserByUsernameWithBcryptPassword() {
        String bcrypt = "$2a$10$abcdefghijklmnopqrstuuABCDEFGHIJKLMNOPQRSTUVWXYZ01234";
        UserEntity user = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        user.setPasswordUser(bcrypt);

        when(userRepository.findByEmail("juan@mail.escuelaing.edu.co")).thenReturn(user);

        var details = userService.loadUserByUsername("juan@mail.escuelaing.edu.co");

        assertNotNull(details);
        verify(passwordEncoder, never()).encode(any());
    }

    @DisplayName("Should throw UsernameNotFoundException when user does not exist")
    @Test
    void shouldFailLoadUserByUsernameWhenNotFound() {
        when(userRepository.findByEmail("ghost@mail.escuelaing.edu.co")).thenReturn(null);

        assertThrows(org.springframework.security.core.userdetails.UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("ghost@mail.escuelaing.edu.co"));
    }

    @DisplayName("Should throw UsernameNotFoundException when stored password is blank")
    @Test
    void shouldFailLoadUserByUsernameWhenPasswordIsBlank() {
        UserEntity user = entityWith(1L, "juan@mail.escuelaing.edu.co", "Juan", true);
        user.setPasswordUser("  ");

        when(userRepository.findByEmail("juan@mail.escuelaing.edu.co")).thenReturn(user);

        assertThrows(org.springframework.security.core.userdetails.UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("juan@mail.escuelaing.edu.co"));
    }
}