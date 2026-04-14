package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.AuthRequest;
import edu.eci.dosw.tech_cup.model.AuthResponse;
import edu.eci.dosw.tech_cup.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * REST controller for authentication-related endpoints using JWT.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Operaciones para autenticación y generación de JWT")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authManager,
                          JwtService jwtService,
                          UserDetailsService userDetailsService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * ¿Para qué sirve UsernamePasswordAuthenticationToken?
     * Sirve para encapsular las credenciales (username y password) que se envían
     * al AuthenticationManager para que Spring Security ejecute el proceso de autenticación.
     */
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Valida credenciales y retorna un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    public AuthResponse login(@RequestBody AuthRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtService.generateToken(user);
            return new AuthResponse(token);
        } catch (AuthenticationException ex) {
            throw new ResponseStatusException(UNAUTHORIZED, "Credenciales inválidas");
        }
    }
}