package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.RoleType;
import edu.eci.dosw.tech_cup.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for {@link UserService} covering CRUD operations, email validation,
 * and user lifecycle management.
 *
 * <p>Tests use the Given/When/Then pattern to clearly separate test setup,
 * execution, and verification of expected behavior.</p>
 */
public class UserServiceTest {

    private IUserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }


    /**
     * Given a valid student player with institutional email
     * When creating the user
     * Then the user is created with an assigned id and active status.
     */
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

    /**
     * Given a student player with a non-institutional email
     * When creating the user
     * Then a RuntimeException is thrown (invalid email for role).
     */
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

    /**
     * Given a valid graduate player with institutional email
     * When creating the user
     * Then the user is created successfully.
     */
    @DisplayName("Should create a graduate user with a valid institutional email")
    @Test
    void shouldCreateGraduateWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.GRADUATE);
        user.setEmail("ana@mail.escuelaing.edu.co");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    /**
     * Given a graduate player with an invalid email domain
     * When creating the user
     * Then a RuntimeException is thrown.
     */
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

    /**
     * Given a valid professor player with escuelaing.edu.co email (no "mail" subdomain)
     * When creating the user
     * Then the user is created successfully.
     */
    @DisplayName("Should create a professor user with a valid professor email")
    @Test
    void shouldCreateProfessorWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.PROFESSOR);
        user.setEmail("profe@escuelaing.edu.co");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    /**
     * Given a professor player with invalid email format (contains "mail" subdomain)
     * When creating the user
     * Then a RuntimeException is thrown.
     */
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

    /**
     * Given a valid administrative player with escuelaing.edu.co email
     * When creating the user
     * Then the user is created successfully.
     */
    @DisplayName("Should create an administrative user with a valid institutional email")
    @Test
    void shouldCreateAdministrativeWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.ADMINISTRATIVE_PERSONAL);
        user.setEmail("admin@escuelaing.edu.co");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    /**
     * Given an administrative player with a non-institutional email
     * When creating the user
     * Then a RuntimeException is thrown.
     */
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

    /**
     * Given a valid family player with a gmail.com email
     * When creating the user
     * Then the user is created successfully.
     */
    @DisplayName("Should create a family user with a valid external email")
    @Test
    void shouldCreateFamilyWithValidEmail() {
        Player user = new Player();
        user.setRole(RoleType.FAMILY);
        user.setEmail("mama@gmail.com");

        User result = userService.createUser(user);

        assertNotNull(result.getId());
    }

    /**
     * Given a family player with an institutional email
     * When creating the user
     * Then a RuntimeException is thrown.
     */
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

    /**
     * Given a previously created user
     * When retrieving the user by id
     * Then the user is found with correct name and email.
     */
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

    /**
     * Given a non-existent user id
     * When retrieving a user
     * Then a RuntimeException is thrown.
     */
    @DisplayName("Should throw an exception when getting a non-existing user by id")
    @Test
    void shouldFailWhenUserNotFound() {
        assertThrows(RuntimeException.class, () -> {
            userService.getUser(999L);
        });
    }

    /**
     * Given multiple created users in the system
     * When retrieving all users
     * Then all users are returned in the list.
     */
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

    /**
     * Given an empty user database
     * When retrieving all users
     * Then an empty list is returned.
     */
    @DisplayName("Should return an empty list when there are no users")
    @Test
    void shouldReturnEmptyListWhenNoUsers() {
        List<User> users = userService.getAllUsers();

        assertTrue(users.isEmpty());
    }

    /**
     * Given a previously created user
     * When updating user basic information (name)
     * Then the user is updated with new values.
     */
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

    /**
     * Given a non-existent user id
     * When attempting to update a user
     * Then a RuntimeException is thrown.
     */
    @DisplayName("Should throw an exception when updating a non-existing user")
    @Test
    void shouldFailUpdateIfUserNotFound() {
        Player updated = new Player();
        updated.setName("Test");

        assertThrows(RuntimeException.class, () -> {
            userService.updateUser(999L, updated);
        });
    }

    /**
     * Given a professor user
     * When updating the email with a valid escuelaing.edu.co domain
     * Then the email is updated successfully.
     */
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

    /**
     * Given a student user
     * When updating email to an invalid domain for the role
     * Then a RuntimeException is thrown.
     */
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

    /**
     * Given an active user
     * When deactivating the user
     * Then the user's active status is set to false.
     */
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

    /**
     * Given a non-existent user id
     * When attempting to deactivate a user
     * Then a RuntimeException is thrown.
     */
    @DisplayName("Should throw an exception when deactivating a non-existing user")
    @Test
    void shouldFailDeactivateIfUserNotFound() {
        assertThrows(RuntimeException.class, () -> {
            userService.deactivateUser(999L);
        });
    }

    /**
     * Given a deactivated user
     * When retrieving all users
     * Then the deactivated user remains in the list (not deleted).
     */
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

    /**
     * Given a null user payload
     * When creating a user
     * Then a RuntimeException is thrown.
     */
    @DisplayName("Should throw an exception when creating a null user")
    @Test
    void shouldFailCreateWhenUserIsNull() {
        assertThrows(RuntimeException.class, () -> userService.createUser(null));
    }

    /**
     * Given a user without a role assigned
     * When creating the user
     * Then a RuntimeException is thrown.
     */
    @DisplayName("Should throw an exception when creating a user without role")
    @Test
    void shouldFailCreateWhenRoleIsNull() {
        Player user = new Player();
        user.setEmail("no-role@mail.escuelaing.edu.co");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    /**
     * Given a user with a blank (whitespace-only) email
     * When creating the user
     * Then a RuntimeException is thrown.
     */
    @DisplayName("Should throw an exception when creating a user with blank email")
    @Test
    void shouldFailCreateWhenEmailIsBlank() {
        Player user = new Player();
        user.setRole(RoleType.STUDENT);
        user.setEmail("   ");

        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    /**
     * Given two users with the same email
     * When creating the second user
     * Then a RuntimeException is thrown (email uniqueness violation).
     */
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

    /**
     * Given a user id and null update payload
     * When attempting to update a user
     * Then a RuntimeException is thrown.
     */
    @DisplayName("Should throw an exception when updating a user with null payload")
    @Test
    void shouldFailUpdateWhenPayloadIsNull() {
        Player user = new Player();
        user.setRole(RoleType.FAMILY);
        user.setEmail("family@gmail.com");

        User created = userService.createUser(user);

        assertThrows(RuntimeException.class, () -> userService.updateUser(created.getId(), null));
    }

    /**
     * Given a user that is updated
     * When applying the update
     * Then the user's id remains unchanged.
     */
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
