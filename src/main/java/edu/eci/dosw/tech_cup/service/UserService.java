package edu.eci.dosw.tech_cup.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.RoleType;
import edu.eci.dosw.tech_cup.model.User;

public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public User createUser(User user) {
        log.debug("Creando usuario con email: {}", user != null ? user.getEmail() : "null");
        try {
            if (user == null) {
                log.error("Intento de crear usuario con datos nulos");
                throw new RuntimeException("User cannot be null");
            }
            if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
                log.error("Email requerido para crear usuario");
                throw new RuntimeException("Email is required");
            }

            boolean exists = users.stream()
                    .anyMatch(u -> u.getEmail().equals(user.getEmail()));
            if (exists) {
                log.warn("Intento de crear usuario con email ya existente: {}", user.getEmail());
                throw new RuntimeException("Email already exists");
            }

            if (user instanceof Player) {
                Player p = (Player) user;
                if (p.getRole() == null) {
                    log.error("Rol requerido para crear usuario: {}", user.getEmail());
                    throw new RuntimeException("Role is required");
                }
                if (!isValidEmail(p.getEmail(), p.getRole())) {
                    log.error("Email inválido para el rol del usuario: {}", user.getEmail());
                    throw new RuntimeException("Invalid email for role");
                }
            }

            user.setId(idCounter++);
            user.setStatus(true);
            users.add(user);
            log.info("Usuario creado correctamente con email: {}", user.getEmail());
            return user;
        } catch (RuntimeException e) {
            log.error("Error creando usuario", e);
            throw e;
        }
    }

    @Override
    public User getUser(Long id) {
        log.debug("Buscando usuario con id: {}", id);
        try {
            User user = users.stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User not found"));
            log.info("Usuario encontrado con id: {}", id);
            return user;
        } catch (RuntimeException e) {
            log.error("Error buscando usuario con id: {}", id, e);
            throw e;
        }
    }

    @Override
    public List<User> getAllUsers() {
        log.debug("Obteniendo lista de todos los usuarios");
        try {
            log.info("Se devuelven {} usuarios", users.size());
            return new ArrayList<>(users);
        } catch (Exception e) {
            log.error("Error obteniendo lista de usuarios", e);
            throw e;
        }
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        log.debug("Actualizando usuario con id: {}", id);
        try {
            if (updatedUser == null) {
                log.error("Intento de actualizar usuario {} con datos nulos", id);
                throw new RuntimeException("Update data cannot be null");
            }

            User existing = getUser(id);

            if (updatedUser.getEmail() != null) {
                if (updatedUser.getEmail().trim().isEmpty()) {
                    log.error("Email vacío para actualizar usuario: {}", id);
                    throw new RuntimeException("Email cannot be empty");
                }
                boolean exists = users.stream()
                        .anyMatch(u -> u.getEmail().equals(updatedUser.getEmail())
                                && !u.getId().equals(id));
                if (exists) {
                    log.warn("Intento de actualizar usuario {} con email ya existente: {}", id, updatedUser.getEmail());
                    throw new RuntimeException("Email already exists");
                }
                if (existing instanceof Player) {
                    Player p = (Player) existing;
                    if (!isValidEmail(updatedUser.getEmail(), p.getRole())) {
                        log.error("Email inválido para el rol del usuario: {}", id);
                        throw new RuntimeException("Invalid email for role");
                    }
                }
                existing.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getName() != null) {
                existing.setName(updatedUser.getName());
            }

            log.info("Usuario actualizado correctamente con id: {}", id);
            return existing;
        } catch (RuntimeException e) {
            log.error("Error actualizando usuario con id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void deactivateUser(Long id) {
        log.debug("Desactivando usuario con id: {}", id);
        try {
            User user = getUser(id);
            user.setStatus(false);
            log.info("Usuario desactivado correctamente con id: {}", id);
        } catch (RuntimeException e) {
            log.error("Error desactivando usuario con id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void authenticate(String email, String password) {
        log.debug("Autenticando usuario con email: {}", email);
        try {
            if (email == null || email.trim().isEmpty()) {
                log.error("Email requerido para autenticación");
                throw new RuntimeException("Email is required");
            }
            if (password == null || password.trim().isEmpty()) {
                log.error("Contraseña requerida para autenticación");
                throw new RuntimeException("Password is required");
            }
            users.stream()
                    .filter(u -> u.getEmail().equals(email)
                            && u.getPassword().equals(password)
                            && Boolean.TRUE.equals(u.getStatus()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Invalid credentials"));
            log.info("Autenticación exitosa para usuario: {}", email);
        } catch (RuntimeException e) {
            log.error("Error en autenticación para usuario: {}", email, e);
            throw e;
        }
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