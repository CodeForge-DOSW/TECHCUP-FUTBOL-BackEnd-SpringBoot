package edu.eci.dosw.tech_cup.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    // CREATE

    @DisplayName("Create student user with a valid institutional email")
    @Test
    void shouldCreateStudentWithValidEmail() {
        User user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");
        user.setName("Juan");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
        assertEquals("Juan", result.getName());
        assertTrue(result.isActive());
    }

    @DisplayName("Reject student user creation with a non-institutional email")
    @Test
    void shouldFailCreateStudentWithInvalidEmail() {
        User user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @DisplayName("Create graduate user with a valid institutional email")
    @Test
    void shouldCreateGraduateWithValidEmail() {
        User user = new Player();
        user.setRole(RoleType.GRADUATE);
        user.setEmail("ana@mail.escuelaing.edu.co");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    @DisplayName("Reject graduate user creation with a non-institutional email")
    @Test
    void shouldFailCreateGraduateWithInvalidEmail() {
        User user = new Player();
        user.setRole(RoleType.GRADUATE);
        user.setEmail("ana@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @DisplayName("Create professor user with an allowed domain")
    @Test
    void shouldCreateProfessorWithValidEmail() {
        User user = new Player();
        user.setRole(RoleType.PROFESSOR);
        user.setEmail("profe@escuelaing.edu.co");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    @DisplayName("Reject professor user creation with a disallowed subdomain")
    @Test
    void shouldFailCreateProfessorWithInvalidEmail() {
        User user = new Player();
        user.setRole(RoleType.PROFESSOR);
        user.setEmail("profe@mail.escuelaing.edu.co");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @DisplayName("Create administrative user with an allowed domain")
    @Test
    void shouldCreateAdministrativeWithValidEmail() {
        User user = new Player();
        user.setRole(RoleType.ADMINISTRATIVE);
        user.setEmail("admin@escuelaing.edu.co");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    @DisplayName("Reject administrative user creation with an invalid domain")
    @Test
    void shouldFailCreateAdministrativeWithInvalidEmail() {
        User user = new Player();
        user.setRole(RoleType.ADMINISTRATIVE);
        user.setEmail("admin@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @DisplayName("Create family user with an external email")
    @Test
    void shouldCreateFamilyWithValidEmail() {
        User user = new Player();
        user.setRole(RoleType.FAMILY);
        user.setEmail("mama@gmail.com");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    @DisplayName("Reject family user creation with an institutional email")
    @Test
    void shouldFailCreateFamilyWithInvalidEmail() {
        User user = new Player();
        user.setRole(RoleType.FAMILY);
        user.setEmail("mama@escuelaing.edu.co");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    // READ

    @DisplayName("Get an existing user by id")
    @Test
    void shouldGetUserById() {
        User user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");
        user.setName("Juan");

        User created = userService.createUser(user);
        User found = userService.getUser(created.getId());

        assertEquals("Juan", found.getName());
        assertEquals("juan@mail.escuelaing.edu.co", found.getEmail());
    }

    @DisplayName("Throw exception when getting a user that does not exist")
    @Test
    void shouldFailWhenUserNotFound() {
        assertThrows(RuntimeException.class, () -> {
            userService.getUser(999L);
        });
    }

    @DisplayName("Return all created users")
    @Test
    void shouldGetAllUsers() {
        User u1 = new Player();
        u1.setRole(RoleType.STUDENT);
        u1.setEmail("a@mail.escuelaing.edu.co");

        User u2 = new Player();
        u2.setRole(RoleType.FAMILY);
        u2.setEmail("b@gmail.com");

        userService.createUser(u1);
        userService.createUser(u2);

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
    }

    @DisplayName("Return an empty list when there are no users")
    @Test
    void shouldReturnEmptyListWhenNoUsers() {
        List<User> users = userService.getAllUsers();

        assertTrue(users.isEmpty());
    }

    // UPDATE

    @DisplayName("Update an existing user")
    @Test
    void shouldUpdateUser() {
        User user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");
        user.setName("Old Name");

        User created = userService.createUser(user);

        User updated = new Player();
        updated.setName("New Name");
        updated.setEmail("juan@mail.escuelaing.edu.co");

        User result = userService.updateUser(created.getId(), updated);

        assertEquals("New Name", result.getName());
    }

    @DisplayName("Throw exception when updating a user that does not exist")
    @Test
    void shouldFailUpdateIfUserNotFound() {
        User updated = new Player();
        updated.setName("Test");

        assertThrows(RuntimeException.class, () -> {
            userService.updateUser(999L, updated);
        });
    }

    @DisplayName("Update user email when the new email is valid for the role")
    @Test
    void shouldUpdateUserWithValidEmail() {
        User user = new Player();
        user.setRole(RoleType.PROFESSOR);
        user.setEmail("profe@escuelaing.edu.co");

        User created = userService.createUser(user);

        User updated = new Player();
        updated.setEmail("profe@escuelaing.edu.co");

        User result = userService.updateUser(created.getId(), updated);

        assertEquals("profe@escuelaing.edu.co", result.getEmail());
    }

    @DisplayName("Reject user update when the new email is invalid for the role")
    @Test
    void shouldFailUpdateWithInvalidEmail() {
        User user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");

        User created = userService.createUser(user);

        User updated = new Player();
        updated.setEmail("juan@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            userService.updateUser(created.getId(), updated);
        });
    }

    // DELETE (INACTIVAR)

    @DisplayName("Deactivate an existing user")
    @Test
    void shouldDeactivateUser() {
        User user = new Player();
        user.setRole(RoleType.FAMILY);
        user.setEmail("mama@gmail.com");
        user.setName("Maria");

        User created = userService.createUser(user);

        userService.deactivateUser(created.getId());

        User result = userService.getUser(created.getId());

        assertFalse(result.isActive());
    }

    @DisplayName("Throw exception when deactivating a user that does not exist")
    @Test
    void shouldFailDeactivateIfUserNotFound() {
        assertThrows(RuntimeException.class, () -> {
            userService.deactivateUser(999L);
        });
    }

    @DisplayName("Keep user in list after deactivation and mark as inactive")
    @Test
    void shouldNotDeleteUserFromList() {
        User user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");

        User created = userService.createUser(user);

        userService.deactivateUser(created.getId());

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertFalse(users.get(0).isActive());
    }
}