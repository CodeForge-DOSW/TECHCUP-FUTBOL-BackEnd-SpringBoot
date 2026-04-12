package edu.eci.dosw.tech_cup.security;

import edu.eci.dosw.tech_cup.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // Bearer es el esquema estándar del header Authorization para indicar
        // que el cliente envía un token de acceso (por ejemplo, un JWT).
        // Sirve para transportar credenciales tokenizadas entre cliente y servidor.
        // 1. Validar si existe el header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extraer token
        String token = authHeader.substring(7);

        // 3. Extraer username
        String username = jwtService.extractUsername(token);

        // SecurityContextHolder mantiene el contexto de seguridad del hilo actual.
        // Sirve para consultar y registrar la autenticación del usuario durante la request.
        // 4. Validar si no está autenticado aún
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // (validación básica)
            if (jwtService.extractUsername(token).equals(userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // WebAuthenticationDetailsSource construye detalles de la request
                // (IP remota, sesión, etc.) y los asocia al token autenticado.
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 5. Registrar usuario como autenticado
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
