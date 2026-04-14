package edu.eci.dosw.tech_cup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * ¿Para qué sirve @EnableWebSecurity?
 * Habilita la infraestructura de Spring Security para la aplicación web,
 * permitiendo registrar y aplicar configuraciones de autenticación/autorización.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @org.springframework.context.annotation.Lazy
    private JwtAuthenticationFilter jwtFilter;

    /**
     * i. http.csrf corresponde a la protección contra ataques CSRF.
     * En este caso se deshabilita porque la API usa JWT (stateless) y no sesión por formulario.
     *
     * ii. authorizeHttpRequests define reglas de autorización:
     * permite acceso público a /auth/** y exige autenticación en el resto de endpoints.
     *
     * iii. addFilterBefore registra JwtAuthenticationFilter antes de UsernamePasswordAuthenticationFilter
     * para validar el token y autenticar al usuario antes del flujo de autenticación por usuario/clave.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * ¿Qué es y para qué sirve AuthenticationManager?
     * Es el componente central de Spring Security que recibe una solicitud
     * de autenticación y delega su validación a los proveedores configurados.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * a. ¿Qué es y para qué sirve PasswordEncoder?
     * Es una estrategia para codificar y verificar contraseñas de forma segura.
     *
     * b. ¿Qué es y para qué sirve BCryptPasswordEncoder?
     * Es una implementación de PasswordEncoder basada en BCrypt, diseñada para
     * almacenar contraseñas con hash y salt, evitando guardar texto plano.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
