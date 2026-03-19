package edu.eci.dosw.tech_cup.service;


import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.RoleType;
import edu.eci.dosw.tech_cup.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    private final List<User> users = new ArrayList<>();
    private Long idCounter = 1L;


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


    @Override
    public User getUser(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users); // evita modificar la original
    }


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


    @Override
    public void deactivateUser(Long id) {
        User user = getUser(id);
        user.setStatus(false);
    }


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