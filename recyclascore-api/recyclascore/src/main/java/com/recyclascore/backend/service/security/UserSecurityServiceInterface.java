package com.recyclascore.backend.service.security;

import com.recyclascore.backend.entity.dto.BearerToken;
import com.recyclascore.backend.entity.dto.LoginDto;
import com.recyclascore.backend.entity.dto.SignUpDto;
import org.springframework.http.ResponseEntity;

public interface UserSecurityServiceInterface {

    /**
     * enregistre le nouveau user en base
     * @param signUpDto : donnees en entree
     * @return le BearerToken ou msg d'erreur avec statut en erreur
     */
    ResponseEntity<?> register(SignUpDto signUpDto);

    /**
     * authentifie le user
     * @param loginDto : donnees en entree
     * @return le BearerToken ou erreur d'authentification
     */
    BearerToken authenticate(LoginDto loginDto);
}
