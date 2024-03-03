package com.recyclascore.backend.config;

import com.recyclascore.backend.service.security.DemoUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtUtilities jwtUtilities;
    @Autowired
    private DemoUserDetailsService demoUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get token in request
        String token = jwtUtilities.getToken(request);
        // If token is ok
        if(token != null && jwtUtilities.validateToken(token)) {
            // Extract email of token
            String email = jwtUtilities.extractEmail(token);
            // Get the user
            UserDetails userDetails = demoUserDetailsService.loadUserByUsername(email);
            if (userDetails != null) {
                // Authenticate user
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                log.debug("authenticated user with email :{}", email);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the user in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // continue the request processing chain
        filterChain.doFilter(request, response);
    }
}
