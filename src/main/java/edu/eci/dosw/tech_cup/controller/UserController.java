package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.User;
import edu.eci.dosw.tech_cup.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller that exposes CRUD operations for users.
 *
 * <p>All business rules are delegated to {@link IUserService}. This class is
 * responsible for HTTP mapping and response status handling.</p>
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UserController {

    /**
     * Service that executes user-related use cases.
     */
    private final IUserService userService;

    /**
     * Builds the controller with its service dependency.
     *
     * @param userService injected user service implementation
     */
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user from a player payload.
     *
     * @param user request payload with user data
     * @return 201 with the created user; 400 with an error message when validation/business rules fail
     */
    @PostMapping
    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en el sistema")
    public ResponseEntity<?> createUser(@RequestBody Player user) {
        try {
            User created = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Retrieves all registered users.
     *
     * @return 200 with the complete user list
     */
    @GetMapping
    @Operation(summary = "Listar usuarios", description = "Obtiene todos los usuarios registrados")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retrieves one user by its id.
     *
     * @param id unique user identifier
     * @return 200 with the user when found; 404 with an error message when not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Busca un usuario usando su identificador")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Updates an existing user.
     *
     * @param id unique user identifier
     * @param user payload containing updated user data
     * @return 200 with the updated user; 400 with an error message when the update is invalid
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Player user) {
        try {
            User updated = userService.updateUser(id, user);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Deactivates a user account.
     *
     * @param id unique user identifier
     * @return 204 No Content when deactivation succeeds; 404 with an error message when the user does not exist
     */
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Desactivar usuario", description = "Desactiva un usuario existente")
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        try {
            userService.deactivateUser(id);
            return ResponseEntity.ok("User deactivated");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}