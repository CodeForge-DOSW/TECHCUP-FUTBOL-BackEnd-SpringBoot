package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.dto.LoginRequest;
import edu.eci.dosw.tech_cup.service.IUserService;
import edu.eci.dosw.tech_cup.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IUserService userService = new UserService();


    @org.springframework.web.bind.annotation.PostMapping("/login")
    public ResponseEntity<?> login(@org.springframework.web.bind.annotation.RequestBody LoginRequest loginRequest) {
        try {
            userService.authenticate(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );
            return ResponseEntity.ok("Login successful");
        } catch (RuntimeException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}