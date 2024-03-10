package com.recyclascore.backend.service.security;

import com.recyclascore.backend.config.JwtUtilities;
import com.recyclascore.backend.entity.Role;
import com.recyclascore.backend.entity.RoleEnum;
import com.recyclascore.backend.entity.User;
import com.recyclascore.backend.entity.dto.BearerToken;
import com.recyclascore.backend.entity.dto.LoginDto;
import com.recyclascore.backend.entity.dto.SignUpDto;
import com.recyclascore.backend.repository.RoleRepository;
import com.recyclascore.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserSecurityService implements UserSecurityServiceInterface{
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private static final String TOKEN_TYPE = "Bearer";

    public UserSecurityService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtilities jwtUtilities, UserRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtilities = jwtUtilities;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> register(SignUpDto signUpDto) {

        // create user in DB
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        //  role for the user
        Role roles = roleRepository.findByRoleName(RoleEnum.USER).orElseThrow();
        user.setRoles(Collections.singleton(roles));

        // save user
        userRepository.save(user);

        //create token
        String token = jwtUtilities.generateToken(signUpDto.getEmail(), Collections.singletonList(roles.getRoleName()));
        // send token inside the response
        return new ResponseEntity<>(new BearerToken(token, TOKEN_TYPE), HttpStatus.OK);
    }
    @Override
    public BearerToken authenticate(LoginDto loginDto) {
        // Auth user
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get user to create her token
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // user roles
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getRoleName()));
        return new BearerToken(jwtUtilities.generateToken(user.getEmail(), rolesNames), TOKEN_TYPE);
    }
}
