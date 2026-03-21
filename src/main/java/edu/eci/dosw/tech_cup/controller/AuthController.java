package edu.eci.dosw.tech_cup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import edu.eci.dosw.tech_cup.dto.LoginRequest;
import edu.eci.dosw.tech_cup.service.IUserService;
import edu.eci.dosw.tech_cup.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication.")
public class AuthController {

    private final IUserService userService = new UserService();

    @PostMapping("/login")
    @Operation(
            summary = "User login",
            description = "Authenticates a user with email and password. Returns 200 on success and 401 when credentials are invalid."
    )
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            userService.authenticate(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );
            return ResponseEntity.ok("Login successful");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}