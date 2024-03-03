package com.recyclascore.backend.controller;

import com.recyclascore.backend.entity.dto.BearerToken;
import com.recyclascore.backend.entity.dto.LoginDto;
import com.recyclascore.backend.entity.dto.SignUpDto;
import com.recyclascore.backend.service.security.UserSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
public class LoginController {

    @Autowired
    private UserSecurityService userSecurityService;

    @PostMapping(value = "/signin")
    public BearerToken authenticateUser(@RequestBody LoginDto loginDto) {
        return userSecurityService.authenticate(loginDto);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        return userSecurityService.register(signUpDto);
    }
}
