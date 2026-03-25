package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.PlayerModel;
import edu.eci.dosw.tech_cup.model.UserRoleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private IUserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @DisplayName("Should create a user successfully with valid data")
    @Test
    void shouldCreateUser() {
        PlayerModel user = new PlayerModel();
        user.setEmail("juan@mail.com");
        user.setName("Juan");

        UserRoleModel result = userService.createUser(user);

        assertNotNull(result.getId());
        assertEquals("Juan", result.getName());
        assertTrue(result.isActive());
    }

    @DisplayName("Should throw exception when creating user with null")
    @Test
    void shouldFailCreateWhenUserIsNull() {
        assertThrows(RuntimeException.class, () -> userService.createUser(null));
    }

    @DisplayName("Should throw exception when email is null")
    @Test
    void shouldFailCreateWhenEmailIsNull() {
        PlayerModel user = new PlayerModel();
        user.setName("Juan");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    @DisplayName("Should throw exception when email is blank")
    @Test
    void shouldFailCreateWhenEmailIsBlank() {
        PlayerModel user = new PlayerModel();
        user.setEmail("   ");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    @DisplayName("Should throw exception when email already exists")
    @Test
    void shouldFailCreateWhenEmailExists() {
        PlayerModel u1 = new PlayerModel();
        u1.setEmail("test@mail.com");

        PlayerModel u2 = new PlayerModel();
        u2.setEmail("test@mail.com");

        userService.createUser(u1);

        assertThrows(RuntimeException.class, () -> userService.createUser(u2));
    }

    @DisplayName("Should return user by id")
    @Test
    void shouldGetUserById() {
        PlayerModel user = new PlayerModel();
        user.setEmail("juan@mail.com");
        user.setName("Juan");

        UserRoleModel created = userService.createUser(user);
        UserRoleModel found = userService.getUser(created.getId());

        assertEquals("Juan", found.getName());
    }

    @DisplayName("Should throw exception when user not found")
    @Test
    void shouldFailGetUser() {
        assertThrows(RuntimeException.class, () -> userService.getUser(999L));
    }

    @DisplayName("Should return all users")
    @Test
    void shouldGetAllUsers() {
        PlayerModel u1 = new PlayerModel();
        u1.setEmail("a@mail.com");

        PlayerModel u2 = new PlayerModel();
        u2.setEmail("b@mail.com");

        userService.createUser(u1);
        userService.createUser(u2);

        List<UserRoleModel> users = userService.getAllUsers();

        assertEquals(2, users.size());
    }

    @DisplayName("Should return empty list when no users")
    @Test
    void shouldReturnEmptyList() {
        assertTrue(userService.getAllUsers().isEmpty());
    }

    @DisplayName("Should update user name")
    @Test
    void shouldUpdateUser() {
        PlayerModel user = new PlayerModel();
        user.setEmail("juan@mail.com");
        user.setName("Old");

        UserRoleModel created = userService.createUser(user);

        PlayerModel updated = new PlayerModel();
        updated.setName("New");

        UserRoleModel result = userService.updateUser(created.getId(), updated);

        assertEquals("New", result.getName());
    }

    @DisplayName("Should throw exception when updating non-existing user")
    @Test
    void shouldFailUpdateUser() {
        PlayerModel updated = new PlayerModel();
        updated.setName("Test");

        assertThrows(RuntimeException.class,
                () -> userService.updateUser(999L, updated));
    }

    @DisplayName("Should deactivate user")
    @Test
    void shouldDeactivateUser() {
        PlayerModel user = new PlayerModel();
        user.setEmail("test@mail.com");

        UserRoleModel created = userService.createUser(user);

        userService.deactivateUser(created.getId());

        assertFalse(userService.getUser(created.getId()).isActive());
    }

    @DisplayName("Should keep user after deactivation")
    @Test
    void shouldKeepUserAfterDeactivate() {
        PlayerModel user = new PlayerModel();
        user.setEmail("test@mail.com");

        UserRoleModel created = userService.createUser(user);

        userService.deactivateUser(created.getId());

        List<UserRoleModel> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertFalse(users.get(0).isActive());
    }

    @DisplayName("Should throw exception when updating with null payload")
    @Test
    void shouldFailUpdateWithNull() {
        PlayerModel user = new PlayerModel();
        user.setEmail("test@mail.com");

        UserRoleModel created = userService.createUser(user);

        assertThrows(RuntimeException.class,
                () -> userService.updateUser(created.getId(), null));
    }

    @DisplayName("Should keep same id after update")
    @Test
    void shouldKeepSameId() {
        PlayerModel user = new PlayerModel();
        user.setEmail("test@mail.com");

        UserRoleModel created = userService.createUser(user);

        PlayerModel updated = new PlayerModel();
        updated.setName("Updated");

        UserRoleModel result = userService.updateUser(created.getId(), updated);

        assertEquals(created.getId(), result.getId());
    }
}