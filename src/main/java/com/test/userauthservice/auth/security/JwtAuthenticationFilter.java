package com.test.userauthservice.auth.security;

import com.test.userauthservice.auth.service.impl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtServiceImpl;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Read Authorization Header
        final String authHeader = request.getHeader("Authorization");

        // 2. Check if the header is present and starts with "Bearer "
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("No valid Authorization header found for request: {}", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extract JWT
        String jwtToken =  authHeader.substring(7);

        // 4. Extract Email
        String email = jwtServiceImpl.extractUsername(jwtToken);

        // 5. Authenticate only if not already authenticated
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            // 6. validate JWT
            if(jwtServiceImpl.isAccessTokenValid(jwtToken)){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));

                // 7. Store authenticated user
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                log.info("Authentication Success");
            }
            log.info("Security Context : " + SecurityContextHolder.getContext().getAuthentication());
        }
        // 8. Continue request
        filterChain.doFilter(request, response);
    }
}
