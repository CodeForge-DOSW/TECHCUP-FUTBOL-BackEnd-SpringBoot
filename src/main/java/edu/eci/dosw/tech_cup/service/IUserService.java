package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.UserRoleModel;

import java.util.List;

/**
 * Service contract for user-related CRUD and authentication operations.
 *
 * <p>Defines operations for creating, retrieving, updating, and deactivating users,
 * along with email validation rules based on user role. All implementations must
 * enforce domain-specific constraints.</p>
 */
public interface IUserService {

    /**
     * Creates a new user with role-specific email validation.
     *
     * <p>Email validation depends on user role:
     * - STUDENT/GRADUATE: must end with {@code @mail.escuelaing.edu.co}
     * - PROFESSOR/ADMINISTRATIVE_PERSONAL: must end with {@code @escuelaing.edu.co}
     * - FAMILY: must end with {@code @gmail.com}</p>
     *
     * @param user the user to create
     * @return the created user with assigned id and active status
     * @throws RuntimeException if user is null, validation fails, or email already exists
     */
    UserRoleModel createUser(UserRoleModel user);

    /**
     * Retrieves a user by its unique id.
     *
     * @param id unique user identifier
     * @return the user if found
     * @throws RuntimeException if user does not exist
     */
    UserRoleModel getUser(Long id);

    /**
     * Retrieves all users regardless of active status.
     *
     * @return list of all users (may be empty)
     */
    List<UserRoleModel> getAllUsers();

    /**
     * Updates an existing user's properties.
     *
     * <p>Applies role-specific email validation and ensures email uniqueness.</p>
     *
     * @param id unique user identifier
     * @param updatedUser payload with fields to update
     * @return the updated user
     * @throws RuntimeException if user not found, validation fails, or email already in use
     */
    UserRoleModel updateUser(Long id, UserRoleModel updatedUser);

    /**
     * Deactivates a user account (sets status to inactive).
     *
     * <p>User remains in the system but cannot authenticate.</p>
     *
     * @param id unique user identifier
     * @throws RuntimeException if user does not exist
     */
    void deactivateUser(Long id);

    /**
     * Authenticates a user using email and password.
     *
     * @param email user email address
     * @param password user password
     * @throws RuntimeException if credentials are invalid or user is not active
     */
    void authenticate(String email, String password);
}