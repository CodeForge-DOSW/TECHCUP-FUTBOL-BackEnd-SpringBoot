package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.RoleType;
import edu.eci.dosw.tech_cup.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserServiceTest {

    private IUserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    // CREATE

    @DisplayName("Should create a student user with a valid institutional email")
    @Test
    void shouldCreateStudentWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");
        user.setName("Juan");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
        assertEquals("Juan", result.getName());
        assertTrue(result.isActive());
    }

    @DisplayName("Should throw an exception when creating a student with a non-institutional email")
    @Test
    void shouldFailCreateStudentWithInvalidEmail() {
        Player user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @DisplayName("Should create a graduate user with a valid institutional email")
    @Test
    void shouldCreateGraduateWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.GRADUATE);
        user.setEmail("ana@mail.escuelaing.edu.co");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    @DisplayName("Should throw an exception when creating a graduate with an invalid email domain")
    @Test
    void shouldFailCreateGraduateWithInvalidEmail() {
        Player user = new Player();
        user.setRole(RoleType.GRADUATE);
        user.setEmail("ana@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @DisplayName("Should create a professor user with a valid professor email")
    @Test
    void shouldCreateProfessorWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.PROFESSOR);
        user.setEmail("profe@escuelaing.edu.co");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    @DisplayName("Should throw an exception when creating a professor with an invalid email format")
    @Test
    void shouldFailCreateProfessorWithInvalidEmail() {
        Player user = new Player();
        user.setRole(RoleType.PROFESSOR);
        user.setEmail("profe@mail.escuelaing.edu.co");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @DisplayName("Should create an administrative user with a valid institutional email")
    @Test
    void shouldCreateAdministrativeWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.ADMINISTRATIVE_PERSONAL);
        user.setEmail("admin@escuelaing.edu.co");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    @DisplayName("Should throw an exception when creating an administrative user with an invalid email")
    @Test
    void shouldFailCreateAdministrativeWithInvalidEmail() {
        Player user = new Player();
        user.setRole(RoleType.ADMINISTRATIVE_PERSONAL);
        user.setEmail("admin@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @DisplayName("Should create a family user with a valid external email")
    @Test
    void shouldCreateFamilyWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.FAMILY);
        user.setEmail("mama@gmail.com");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    @DisplayName("Should throw an exception when creating a family user with an institutional email")
    @Test
    void shouldFailCreateFamilyWithInvalidEmail() {
        Player user = new Player();
        user.setRole(RoleType.FAMILY);
        user.setEmail("mama@escuelaing.edu.co");

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    // READ

    @DisplayName("Should return an existing user by id")
    @Test
    void shouldGetUserById() {
        Player user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");
        user.setName("Juan");

        User created = userService.createUser(user);
        User found = userService.getUser(created.getId());

        assertEquals("Juan", found.getName());
        assertEquals("juan@mail.escuelaing.edu.co", found.getEmail());
    }

    @DisplayName("Should throw an exception when getting a non-existing user by id")
    @Test
    void shouldFailWhenUserNotFound() {
        assertThrows(RuntimeException.class, () -> {
            userService.getUser(999L);
        });
    }

    @DisplayName("Should return all created users")
    @Test
    void shouldGetAllUsers() {
        Player u1 = new Player();
        u1.setRole(RoleType.STUDENT);
        u1.setEmail("a@mail.escuelaing.edu.co");

        Player u2 = new Player();
        u2.setRole(RoleType.FAMILY);
        u2.setEmail("b@gmail.com");

        userService.createUser(u1);
        userService.createUser(u2);

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
    }

    @DisplayName("Should return an empty list when there are no users")
    @Test
    void shouldReturnEmptyListWhenNoUsers() {
        List<User> users = userService.getAllUsers();

        assertTrue(users.isEmpty());
    }

    // UPDATE

    @DisplayName("Should update user basic information successfully")
    @Test
    void shouldUpdateUser() {
        Player user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");
        user.setName("Old Name");

        User created = userService.createUser(user);

        Player updated = new Player();
        updated.setName("New Name");
        updated.setEmail("juan@mail.escuelaing.edu.co");

        User result = userService.updateUser(created.getId(), updated);

        assertEquals("New Name", result.getName());
    }

    @DisplayName("Should throw an exception when updating a non-existing user")
    @Test
    void shouldFailUpdateIfUserNotFound() {
        Player updated = new Player();
        updated.setName("Test");

        assertThrows(RuntimeException.class, () -> {
            userService.updateUser(999L, updated);
        });
    }

    @DisplayName("Should update user email when the new email is valid")
    @Test
    void shouldUpdateUserWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.PROFESSOR);
        user.setEmail("profe@escuelaing.edu.co");

        User created = userService.createUser(user);

        Player updated = new Player();
        updated.setEmail("profe@escuelaing.edu.co");

        User result = userService.updateUser(created.getId(), updated);

        assertEquals("profe@escuelaing.edu.co", result.getEmail());
    }

    @DisplayName("Should throw an exception when updating user email with an invalid domain")
    @Test
    void shouldFailUpdateWithInvalidEmail() {
        Player user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");

        User created = userService.createUser(user);

        Player updated = new Player();
        updated.setEmail("juan@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            userService.updateUser(created.getId(), updated);
        });
    }

    // DEACTIVATE

    @DisplayName("Should deactivate an existing user")
    @Test
    void shouldDeactivateUser() {
        Player user = new Player();
        user.setRole(RoleType.FAMILY);
        user.setEmail("mama@gmail.com");
        user.setName("Maria");

        User created = userService.createUser(user);

        userService.deactivateUser(created.getId());

        User result = userService.getUser(created.getId());

        assertFalse(result.isActive());
    }

    @DisplayName("Should throw an exception when deactivating a non-existing user")
    @Test
    void shouldFailDeactivateIfUserNotFound() {
        assertThrows(RuntimeException.class, () -> {
            userService.deactivateUser(999L);
        });
    }

    @DisplayName("Should keep deactivated users in the user list")
    @Test
    void shouldNotDeleteUserFromList() {
        Player user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("juan@mail.escuelaing.edu.co");

        User created = userService.createUser(user);

        userService.deactivateUser(created.getId());

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertFalse(users.get(0).isActive());
    }


    @DisplayName("Should throw an exception when creating a null user")
    @Test
    void shouldFailCreateWhenUserIsNull() {
        assertThrows(RuntimeException.class, () -> userService.createUser(null));
    }

    @DisplayName("Should throw an exception when creating a user without role")
    @Test
    void shouldFailCreateWhenRoleIsNull() {
        Player user = new Player();
        user.setEmail("no-role@mail.escuelaing.edu.co");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    @DisplayName("Should throw an exception when creating a user with blank email")
    @Test
    void shouldFailCreateWhenEmailIsBlank() {
        Player user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("   ");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    @DisplayName("Should throw an exception when creating two users with the same email")
    @Test
    void shouldFailCreateWhenEmailAlreadyExists() {
        Player first = new Player();
        first.setRole(RoleType.STUDENT);
        first.setEmail("duplicate@mail.escuelaing.edu.co");

        Player second = new Player();
        second.setRole(RoleType.STUDENT);
        second.setEmail("duplicate@mail.escuelaing.edu.co");

        userService.createUser(first);

        assertThrows(RuntimeException.class, () -> userService.createUser(second));
    }

    @DisplayName("Should throw an exception when updating a user with null payload")
    @Test
    void shouldFailUpdateWhenPayloadIsNull() {
        Player user = new Player();
        user.setRole(RoleType.FAMILY);
        user.setEmail("family@gmail.com");

        User created = userService.createUser(user);

        assertThrows(RuntimeException.class, () -> userService.updateUser(created.getId(), null));
    }

    @DisplayName("Should keep the same id after updating a user")
    @Test
    void shouldKeepSameIdAfterUpdate() {
        Player user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("same-id@mail.escuelaing.edu.co");
        user.setName("Before");

        User created = userService.createUser(user);

        Player updated = new Player();
        updated.setEmail("same-id@mail.escuelaing.edu.co");
        updated.setName("After");

        User result = userService.updateUser(created.getId(), updated);

        assertEquals(created.getId(), result.getId());
    }
}