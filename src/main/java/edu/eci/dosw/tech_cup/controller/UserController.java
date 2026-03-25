package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.PlayerModel;
import edu.eci.dosw.tech_cup.model.UserRoleModel;
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
 * Controlador REST para operaciones CRUD de usuarios.
 *
 * <p>Recibe {@link PlayerModel} en el cuerpo de las peticiones de escritura,
 * ya que es la implementación concreta de {@code UserRoleModel} que Jackson
 * puede deserializar directamente. Para lecturas devuelve {@link UserRoleModel}
 * (que en tiempo de ejecución siempre es un {@code PlayerModel}).</p>
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints for user management operations")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param user datos del usuario a crear
     * @return 201 con el usuario creado; 400 si hay errores de validación
     */
    @PostMapping
    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en el sistema")
    public ResponseEntity<?> createUser(@RequestBody PlayerModel user) {
    @Operation(summary = "Create user", description = "Registers a new user in the system")
    public ResponseEntity<?> createUser(@RequestBody Player user) {
        try {
            PlayerModel created = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Retorna todos los usuarios registrados.
     *
     * @return 200 con la lista de usuarios
     */
    @GetMapping
    @Operation(summary = "Listar usuarios", description = "Obtiene todos los usuarios registrados")
    public ResponseEntity<List<UserRoleModel>> getAllUsers() {
    @Operation(summary = "List users", description = "Retrieves all registered users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retorna un usuario por su identificador.
     *
     * @param id identificador único
     * @return 200 con el usuario; 404 si no existe
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by identifier")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id   identificador único
     * @param user payload con los campos a actualizar
     * @return 200 con el usuario actualizado; 400 si hay errores de validación
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody PlayerModel user) {
    @Operation(summary = "Update user", description = "Updates information for an existing user")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Player user) {
        try {
            PlayerModel updated = userService.updateUser(id, user);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Desactiva la cuenta de un usuario (borrado lógico).
     *
     * @param id identificador único
     * @return 200 confirmación; 404 si el usuario no existe
     */
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate user", description = "Deactivates an existing user")
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        try {
            userService.deactivateUser(id);
            return ResponseEntity.ok("User deactivated");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}