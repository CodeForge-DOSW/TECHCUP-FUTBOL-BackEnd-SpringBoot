package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.RoleType;
import edu.eci.dosw.tech_cup.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of user management and authentication business logic.
 *
 * <p>Handles CRUD operations with comprehensive validation, enforces role-based email
 * constraints, ensures email uniqueness, and provides authentication services.
 * Currently uses in-memory storage (should be replaced with a database repository).</p>
 */
@Service
public class UserService implements IUserService {

    /**
     * In-memory storage of users (should be replaced with a database repository).
     */
    private final List<User> users = new ArrayList<>();

    /**
     * Identifier generator for new users.
     */
    private Long idCounter = 1L;

    /**
     * Creates a new user with role-specific email validation.
     *
     * <p>Validates all required fields and enforces role-based email constraints:
     * STUDENT/GRADUATE require @mail.escuelaing.edu.co, PROFESSOR/ADMINISTRATIVE_PERSONAL
     * require @escuelaing.edu.co, and FAMILY requires @gmail.com.</p>
     *
     * @param user the user to create (must be cast to Player to access role)
     * @return the persisted user with assigned id and active status
     * @throws RuntimeException if validation fails or email already exists
     */
    @Override
    public User createUser(User user) {
        if (user == null) {
            throw new RuntimeException("User cannot be null");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        boolean exists = users.stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()));
        if (exists) {
            throw new RuntimeException("Email already exists");
        }

        if (user instanceof Player) {
            Player p = (Player) user;
            if (p.getRole() == null) {
                throw new RuntimeException("Role is required");
            }
            if (!isValidEmail(p.getEmail(), p.getRole())) {
                throw new RuntimeException("Invalid email for role");
            }
        }

        user.setId(idCounter++);
        user.setStatus(true);
        users.add(user);
        return user;
    }

    /**
     * Retrieves a user by its id.
     *
     * @param id unique user identifier
     * @return the user if found
     * @throws RuntimeException if user does not exist
     */
    @Override
    public User getUser(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Retrieves all users.
     *
     * @return a copy of the user list
     */
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Updates an existing user's properties.
     *
     * <p>Applies role-specific email validation and ensures email uniqueness.</p>
     *
     * @param id unique user identifier
     * @param updatedUser payload with fields to update
     * @return the updated user
     * @throws RuntimeException if user not found or validation fails
     */
    @Override
    public User updateUser(Long id, User updatedUser) {
        if (updatedUser == null) {
            throw new RuntimeException("Update data cannot be null");
        }

        User existing = getUser(id);

        if (updatedUser.getEmail() != null) {
            if (updatedUser.getEmail().trim().isEmpty()) {
                throw new RuntimeException("Email cannot be empty");
            }
            boolean exists = users.stream()
                    .anyMatch(u -> u.getEmail().equals(updatedUser.getEmail())
                            && !u.getId().equals(id));
            if (exists) {
                throw new RuntimeException("Email already exists");
            }
            if (existing instanceof Player) {
                Player p = (Player) existing;
                if (!isValidEmail(updatedUser.getEmail(), p.getRole())) {
                    throw new RuntimeException("Invalid email for role");
                }
            }
            existing.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getName() != null) {
            existing.setName(updatedUser.getName());
        }

        return existing;
    }

    /**
     * Deactivates a user account.
     *
     * @param id unique user identifier
     * @throws RuntimeException if user does not exist
     */
    @Override
    public void deactivateUser(Long id) {
        User user = getUser(id);
        user.setStatus(false);
    }

    /**
     * Authenticates a user with email and password.
     *
     * <p>Only active users can authenticate. Throws exception if credentials are invalid
     * or user is not active.</p>
     *
     * @param email user email address
     * @param password user password
     * @throws RuntimeException if credentials are invalid or user is not active
     */
    @Override
    public void authenticate(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }
        users.stream()
                .filter(u -> u.getEmail().equals(email)
                        && u.getPassword().equals(password)
                        && Boolean.TRUE.equals(u.getStatus()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    /**
     * Validates email format based on user role.
     *
     * @param email the email address to validate
     * @param role the user's role type
     * @return true if email matches the role's domain; false otherwise
     */
    private boolean isValidEmail(String email, RoleType role) {
        switch (role) {
            case STUDENT:
            case GRADUATE:
                return email.endsWith("@mail.escuelaing.edu.co");
            case PROFESSOR:
            case ADMINISTRATIVE_PERSONAL:
                return email.endsWith("@escuelaing.edu.co");
            case FAMILY:
                return email.endsWith("@gmail.com");
            default:
                return false;
        }
    }
}