package com.recyclascore.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        jsr250Enabled=true //>= pour utiliser annotation @RoleAllowed
)
public class SecurityConfig {

    /**
     * Creation et injection auto des dependances via spring, on aurait pu aussi utiliser l'annotation @RequiredArgsConstructor
     * @param jwtAuthenticationFilter
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter ;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http.cors(cors -> cors.configurationSource(request -> getCorsConfiguration())); // <= cors
        http.csrf(csrf -> csrf.disable()); // <= desactive crsf, nécesaire pour JWT
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // <= pas d'etat de session
        // config des authorisations de base sur requête http
        http.authorizeHttpRequests(
                (authorize) ->
                        authorize
                                .requestMatchers( "/api/auth/**").permitAll() // acces a tous les user
                                .requestMatchers( "/user/**").hasRole("USER") // acces a tous les user USER
                                .requestMatchers( "/admin/**").hasRole("ADMINISTRATOR") // acces a tous les user ADMINISTRATOR
                                .anyRequest().authenticated() // doit etre authentifie sur chaque requete
        );
// Ajout du filtre JWT, permettant de vérifier le token et le rôle de l'utilisateur
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // renvoie la config
        return http.build();
    }

    /**
     * Determine une configuration Cors
     * @return CorsConfiguration
     */
    @Bean
    public CorsConfiguration getCorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // headers
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        //origins (qui à le droit d'appeler, quels hosts)
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        // methodes http
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        //si app securisee avec Authorization header
        corsConfiguration.setAllowCredentials(true);
        //duree validite
        corsConfiguration.setMaxAge(4800L);
        //headers expose en reponse
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        //renvoie la config
        return corsConfiguration;
    }

    // Encodeur de mot de passe utilisant bcrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)  throws  Exception{
        return configuration.getAuthenticationManager();
    }
}
