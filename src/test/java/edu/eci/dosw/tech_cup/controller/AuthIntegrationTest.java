package edu.eci.dosw.tech_cup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.model.AuthRequest;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests that verify the full JWT authentication flow
 * against an in-memory H2 database (profile: test).
 *
 * Covers the four mandatory scenarios from Lab 9:
 *   1. Login con credenciales válidas → genera token
 *   2. Login con credenciales inválidas → 401
 *   3. Endpoint protegido con token válido → 200
 *   4. Endpoint protegido sin token → 401/403
 */
@SpringBootTest
@ActiveProfiles("test")
class AuthIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    private static final String TEST_EMAIL    = "integracion@mail.escuelaing.edu.co";
    private static final String TEST_PASSWORD = "password123";

    @BeforeEach
    void setUp() {
        // Se agrega el FilterChainProxy explícitamente para que Spring Security
        // evalúe cada request antes de que llegue al DispatcherServlet.
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();

        userRepository.deleteAll();

        UserEntity user = new UserEntity(
                "Integration", "Test",
                TEST_EMAIL,
                passwordEncoder.encode(TEST_PASSWORD),
                "999000999",
                LocalDate.of(2000, 6, 15),
                "M"
        );
        user.setUserType("student");
        userRepository.save(user);
    }

    // ------------------------------------------------------------------ //
    // 1. Login éxito
    // ------------------------------------------------------------------ //

    @Test
    @DisplayName("Login con credenciales válidas retorna token JWT")
    void loginSuccess() throws Exception {
        AuthRequest request = new AuthRequest(TEST_EMAIL, TEST_PASSWORD);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    // ------------------------------------------------------------------ //
    // 2. Login error
    // ------------------------------------------------------------------ //

    @Test
    @DisplayName("Login con contraseña incorrecta retorna 401")
    void loginFailure() throws Exception {
        AuthRequest request = new AuthRequest(TEST_EMAIL, "contraseña_incorrecta");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    // ------------------------------------------------------------------ //
    // 3. Endpoint protegido — con token válido
    // ------------------------------------------------------------------ //

    @Test
    @DisplayName("GET /api/users con token válido retorna 200")
    void protectedEndpointWithValidToken() throws Exception {
        String token = obtenerToken();

        mockMvc.perform(get("/api/users")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    // ------------------------------------------------------------------ //
    // 4. Endpoint protegido — sin token
    // ------------------------------------------------------------------ //

    @Test
    @DisplayName("GET /api/users sin token retorna 401 o 403")
    void protectedEndpointWithoutToken() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().is(
                        org.hamcrest.Matchers.anyOf(
                                org.hamcrest.Matchers.is(401),
                                org.hamcrest.Matchers.is(403)
                        )
                ));
    }

    // ------------------------------------------------------------------ //
    // Utilidad
    // ------------------------------------------------------------------ //

    private String obtenerToken() throws Exception {
        AuthRequest loginRequest = new AuthRequest(TEST_EMAIL, TEST_PASSWORD);

        MvcResult result = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        return objectMapper.readTree(body).get("token").asText();
    }
}