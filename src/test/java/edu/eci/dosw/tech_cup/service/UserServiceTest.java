package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.exception.NotFoundException;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.PlayerModel;
import edu.eci.dosw.tech_cup.model.UserRoleModel;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    // ─── Entidades y modelos de apoyo ───────────────────────────────────────

    /**
     * Construye una UserEntity con id y datos básicos para usar como
     * respuesta simulada del repositorio.
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
     * Construye un PlayerModel con id y datos básicos para usar como
     * respuesta simulada del mapper.
     */
    private PlayerModel modelWith(Long id, String email, String name, boolean active) {
        PlayerModel m = new PlayerModel();
        m.setId(id);
        m.setEmail(email);
        m.setName(name);
        m.setStatus(active);
        return m;
    }

    // ─── createUser ─────────────────────────────────────────────────────────

    @DisplayName("Should create a user successfully with valid data")
    @Test
    void shouldCreateUser() {
        PlayerModel input = new PlayerModel();
        input.setEmail("juan@mail.com");
        input.setName("Juan");

        UserEntity savedEntity = entityWith(1L, "juan@mail.com", "Juan", true);
        PlayerModel expectedModel = modelWith(1L, "juan@mail.com", "Juan", true);

        when(userRepository.existsByEmail("juan@mail.com")).thenReturn(false);
        when(userMapper.toEntity(input)).thenReturn(savedEntity);
        when(userRepository.save(savedEntity)).thenReturn(savedEntity);
        when(userMapper.toModel(savedEntity)).thenReturn(expectedModel);

        PlayerModel result = userService.createUser(input);

        assertNotNull(result.getId());
        assertEquals("Juan", result.getName());
        assertTrue(result.isActive());
    }

    @DisplayName("Should throw exception when creating user with null")
    @Test
    void shouldFailCreateWhenUserIsNull() {
        assertThrows(RuntimeException.class, () -> userService.createUser(null));
        verifyNoInteractions(userRepository, userMapper);
    }

    @DisplayName("Should throw exception when email is null")
    @Test
    void shouldFailCreateWhenEmailIsNull() {
        PlayerModel user = new PlayerModel();
        user.setName("Juan");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
        verifyNoInteractions(userRepository, userMapper);
    }

    @DisplayName("Should throw exception when email is blank")
    @Test
    void shouldFailCreateWhenEmailIsBlank() {
        PlayerModel user = new PlayerModel();
        user.setEmail("   ");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
        verifyNoInteractions(userRepository, userMapper);
    }

    @DisplayName("Should throw exception when email already exists")
    @Test
    void shouldFailCreateWhenEmailExists() {
        PlayerModel user = new PlayerModel();
        user.setEmail("test@mail.com");

        when(userRepository.existsByEmail("test@mail.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
        verify(userRepository, never()).save(any());
    }

    // ─── getUser ────────────────────────────────────────────────────────────

    @DisplayName("Should return user by id")
    @Test
    void shouldGetUserById() {
        UserEntity entity = entityWith(1L, "juan@mail.com", "Juan", true);
        PlayerModel model = modelWith(1L, "juan@mail.com", "Juan", true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userMapper.toModel(entity)).thenReturn(model);

        UserRoleModel found = userService.getUser(1L);

        assertEquals("Juan", found.getName());
        assertEquals(1L, found.getId());
    }

    @DisplayName("Should throw exception when user not found")
    @Test
    void shouldFailGetUser() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUser(999L));
    }

    // ─── getAllUsers ─────────────────────────────────────────────────────────

    @DisplayName("Should return all users")
    @Test
    void shouldGetAllUsers() {
        UserEntity e1 = entityWith(1L, "a@mail.com", "Alice", true);
        UserEntity e2 = entityWith(2L, "b@mail.com", "Bob", true);
        PlayerModel m1 = modelWith(1L, "a@mail.com", "Alice", true);
        PlayerModel m2 = modelWith(2L, "b@mail.com", "Bob", true);

        when(userRepository.findAll()).thenReturn(List.of(e1, e2));
        when(userMapper.toModel(e1)).thenReturn(m1);
        when(userMapper.toModel(e2)).thenReturn(m2);

        List<UserRoleModel> users = userService.getAllUsers();

        assertEquals(2, users.size());
    }

    @DisplayName("Should return empty list when no users")
    @Test
    void shouldReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(List.of());

        assertTrue(userService.getAllUsers().isEmpty());
    }

    // ─── updateUser ─────────────────────────────────────────────────────────

    @DisplayName("Should update user name")
    @Test
    void shouldUpdateUser() {
        UserEntity existing = entityWith(1L, "juan@mail.com", "Old", true);
        UserEntity savedEntity = entityWith(1L, "juan@mail.com", "New", true);
        PlayerModel resultModel = modelWith(1L, "juan@mail.com", "New", true);

        PlayerModel updatedPayload = new PlayerModel();
        updatedPayload.setName("New");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(existing)).thenReturn(savedEntity);
        when(userMapper.toModel(savedEntity)).thenReturn(resultModel);

        UserRoleModel result = userService.updateUser(1L, updatedPayload);

        assertEquals("New", result.getName());
    }

    @DisplayName("Should throw exception when updating non-existing user")
    @Test
    void shouldFailUpdateUser() {
        PlayerModel updatedPayload = new PlayerModel();
        updatedPayload.setName("Test");

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userService.updateUser(999L, updatedPayload));
    }

    @DisplayName("Should throw exception when updating with null payload")
    @Test
    void shouldFailUpdateWithNull() {
        assertThrows(RuntimeException.class,
                () -> userService.updateUser(1L, null));
        verifyNoInteractions(userRepository, userMapper);
    }

    @DisplayName("Should keep same id after update")
    @Test
    void shouldKeepSameId() {
        UserEntity existing = entityWith(1L, "test@mail.com", "Old", true);
        UserEntity savedEntity = entityWith(1L, "test@mail.com", "Updated", true);
        PlayerModel resultModel = modelWith(1L, "test@mail.com", "Updated", true);

        PlayerModel updatedPayload = new PlayerModel();
        updatedPayload.setName("Updated");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(existing)).thenReturn(savedEntity);
        when(userMapper.toModel(savedEntity)).thenReturn(resultModel);

        UserRoleModel result = userService.updateUser(1L, updatedPayload);

        assertEquals(1L, result.getId());
    }

    // ─── deactivateUser ──────────────────────────────────────────────────────

    @DisplayName("Should deactivate user")
    @Test
    void shouldDeactivateUser() {
        UserEntity entity = entityWith(1L, "test@mail.com", "Test", true);
        PlayerModel deactivatedModel = modelWith(1L, "test@mail.com", "Test", false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userRepository.save(entity)).thenReturn(entity);

        userService.deactivateUser(1L);

        verify(userRepository).save(entity);
        assertFalse(entity.getStatus());
    }

    @DisplayName("Should throw NotFoundException when deactivating non-existing user")
    @Test
    void shouldThrowNotFoundWhenDeactivatingNonExistingUser() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.deactivateUser(999L));
    }

    @DisplayName("Should keep user after deactivation")
    @Test
    void shouldKeepUserAfterDeactivate() {
        UserEntity entity = entityWith(1L, "test@mail.com", "Test", true);
        PlayerModel deactivatedModel = modelWith(1L, "test@mail.com", "Test", false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userRepository.save(entity)).thenReturn(entity);
        when(userRepository.findAll()).thenReturn(List.of(entity));
        when(userMapper.toModel(entity)).thenReturn(deactivatedModel);

        userService.deactivateUser(1L);

        List<UserRoleModel> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertFalse(users.get(0).isActive());
    }

    // ─── authenticate ───────────────────────────────────────────────────────

    @DisplayName("Should authenticate successfully with valid credentials")
    @Test
    void shouldAuthenticateSuccessfully() {
        UserEntity entity = entityWith(1L, "user@mail.com", "User", true);
        entity.setPasswordUser("secret");
        entity.setStatus(true);

        when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(entity));

        assertDoesNotThrow(() -> userService.authenticate("user@mail.com", "secret"));
    }

    @DisplayName("Should throw exception when authenticating with null email")
    @Test
    void shouldFailAuthenticateWhenEmailIsNull() {
        assertThrows(RuntimeException.class, () -> userService.authenticate(null, "pass"));
        verifyNoInteractions(userRepository);
    }

    @DisplayName("Should throw exception when authenticating with blank email")
    @Test
    void shouldFailAuthenticateWhenEmailIsBlank() {
        assertThrows(RuntimeException.class, () -> userService.authenticate("  ", "pass"));
        verifyNoInteractions(userRepository);
    }

    @DisplayName("Should throw exception when authenticating with null password")
    @Test
    void shouldFailAuthenticateWhenPasswordIsNull() {
        assertThrows(RuntimeException.class, () -> userService.authenticate("user@mail.com", null));
        verifyNoInteractions(userRepository);
    }

    @DisplayName("Should throw exception when user not found during authentication")
    @Test
    void shouldFailAuthenticateWhenUserNotFound() {
        when(userRepository.findByEmail("ghost@mail.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userService.authenticate("ghost@mail.com", "pass"));
    }

    @DisplayName("Should throw exception when password is wrong during authentication")
    @Test
    void shouldFailAuthenticateWhenPasswordIsWrong() {
        UserEntity entity = entityWith(1L, "user@mail.com", "User", true);
        entity.setPasswordUser("correct");
        entity.setStatus(true);

        when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(entity));

        assertThrows(RuntimeException.class,
                () -> userService.authenticate("user@mail.com", "wrong"));
    }

    @DisplayName("Should throw exception when account is inactive during authentication")
    @Test
    void shouldFailAuthenticateWhenAccountIsInactive() {
        UserEntity entity = entityWith(1L, "user@mail.com", "User", false);
        entity.setPasswordUser("secret");
        entity.setStatus(false);

        when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(entity));

        assertThrows(RuntimeException.class,
                () -> userService.authenticate("user@mail.com", "secret"));
    }

    // ─── createUser - role enforcement ─────────────────────────────────────

    @DisplayName("Should assign PLAYER role by default when creating a user")
    @Test
    void shouldAssignPlayerRoleByDefault() {
        PlayerModel input = new PlayerModel();
        input.setEmail("player@mail.com");
        input.setName("Player");

        UserEntity savedEntity = entityWith(1L, "player@mail.com", "Player", true);
        PlayerModel expectedModel = modelWith(1L, "player@mail.com", "Player", true);

        when(userRepository.existsByEmail("player@mail.com")).thenReturn(false);
        when(userMapper.toEntity(any())).thenReturn(savedEntity);
        when(userRepository.save(savedEntity)).thenReturn(savedEntity);
        when(userMapper.toModel(savedEntity)).thenReturn(expectedModel);

        userService.createUser(input);

        assertEquals("PLAYER", input.getRole());
    }

    // ─── getUser / updateUser - NotFoundException ───────────────────────────

    @DisplayName("Should throw NotFoundException when user not found by id")
    @Test
    void shouldThrowNotFoundWhenUserNotFoundById() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getUser(999L));
    }

    @DisplayName("Should throw NotFoundException when updating non-existing user")
    @Test
    void shouldThrowNotFoundWhenUpdatingNonExistingUser() {
        PlayerModel updatedPayload = new PlayerModel();
        updatedPayload.setName("Test");

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> userService.updateUser(999L, updatedPayload));
    }

    // ─── assignRole ─────────────────────────────────────────────────────────

    @DisplayName("Should throw NotFoundException when admin user not found in assignRole")
    @Test
    void shouldThrowNotFoundWhenAdminNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> userService.assignRole(1L, "ORGANIZER", 99L));
    }

    @DisplayName("Should throw exception when caller is not admin in assignRole")
    @Test
    void shouldThrowWhenCallerIsNotAdmin() {
        UserEntity nonAdminEntity = entityWith(2L, "user@mail.com", "User", true);
        PlayerModel nonAdminModel = modelWith(2L, "user@mail.com", "User", true);
        nonAdminModel.setRole("PLAYER");

        when(userRepository.findById(2L)).thenReturn(Optional.of(nonAdminEntity));
        when(userMapper.toModel(nonAdminEntity)).thenReturn(nonAdminModel);

        assertThrows(RuntimeException.class,
                () -> userService.assignRole(1L, "ORGANIZER", 2L));
    }

    @DisplayName("Should throw NotFoundException when target user not found in assignRole")
    @Test
    void shouldThrowNotFoundWhenTargetUserNotFound() {
        UserEntity adminEntity = entityWith(1L, "admin@mail.com", "Admin", true);
        PlayerModel adminModel = modelWith(1L, "admin@mail.com", "Admin", true);
        adminModel.setRole("ADMIN");

        when(userRepository.findById(1L)).thenReturn(Optional.of(adminEntity));
        when(userMapper.toModel(adminEntity)).thenReturn(adminModel);
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> userService.assignRole(999L, "ORGANIZER", 1L));
    }
}