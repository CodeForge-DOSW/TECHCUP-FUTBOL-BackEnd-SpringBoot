package edu.eci.dosw.tech_cup.service;

import java.util.ArrayList;
import java.util.List;

import edu.eci.dosw.tech_cup.model.PlayerModel;
import edu.eci.dosw.tech_cup.model.UserRoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final List<UserRoleModel> users = new ArrayList<>();

    private Long idCounter = 1L;

    @Override
    public UserRoleModel createUser(UserRoleModel user) {
        log.debug("Creating user with email: {}", user != null ? user.getEmail() : "null");

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

        // ✅ VALIDACIÓN GENERAL (sin role)
        if (user instanceof PlayerModel) {
            if (!isValidEmail(user.getEmail())) {
                throw new RuntimeException("Invalid email format");
            }
        }

        user.setId(idCounter++);
        user.setStatus(true);
        users.add(user);

        return user;
    }

    @Override
    public UserRoleModel getUser(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserRoleModel> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public UserRoleModel updateUser(Long id, UserRoleModel updatedUser) {

        if (updatedUser == null) {
            throw new RuntimeException("Update data cannot be null");
        }

        UserRoleModel existing = getUser(id);

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

            if (existing instanceof PlayerModel) {
                if (!isValidEmail(updatedUser.getEmail())) {
                    throw new RuntimeException("Invalid email format");
                }
            }

            existing.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getName() != null) {
            existing.setName(updatedUser.getName());
        }

        return existing;
    }

    @Override
    public void deactivateUser(Long id) {
        UserRoleModel user = getUser(id);
        user.setStatus(false);
    }

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
                        && u.isStatus()) // ✅ CORREGIDO
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    // ✅ VALIDACIÓN SIMPLE (SIN ROLE)
    private boolean isValidEmail(String email) {
        return email.contains("@");
    }
}