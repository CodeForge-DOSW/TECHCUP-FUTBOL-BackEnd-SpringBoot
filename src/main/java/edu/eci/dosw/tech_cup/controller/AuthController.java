package edu.eci.dosw.tech_cup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import edu.eci.dosw.tech_cup.dto.LoginRequest;
import edu.eci.dosw.tech_cup.service.IUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for authentication-related endpoints.
 *
 * <p>This controller currently exposes login functionality and delegates
 * credential validation to {@link IUserService}.</p>
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Operaciones de inicio de sesión")
public class AuthController {

    /**
     * Service that handles user authentication use cases.
     */
    private final IUserService userService;

    /**
     * Creates the authentication controller.
     *
     * @param userService injected user service implementation
     */
    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Authenticates a user using email and password.
     *
     * <p>Validates credentials against the user database. Returns 200 with a success message
     * only if both credentials are valid and the user account is active.</p>
     *
     * @param loginRequest payload containing login credentials (email and password)
     * @return 200 with success message when authentication succeeds; 401 with an error message when it fails
     */
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Permite a un usuario autenticarse con email y contraseña")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok("Login successful");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}