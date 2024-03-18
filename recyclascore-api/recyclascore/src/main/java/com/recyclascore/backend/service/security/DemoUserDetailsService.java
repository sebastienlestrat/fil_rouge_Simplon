package com.recyclascore.backend.service.security;

import com.recyclascore.backend.entity.User;
import com.recyclascore.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class DemoUserDetailsService  implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Recuper le user selon son nom ou son email sachant que chacun est UNIQUE en base
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Utilisateur inconnu : " + email));

        // Recupere les roles du user
        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

        // Renvoie l'objet UserDetails utilise par Spring Security
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities);
    }
}
