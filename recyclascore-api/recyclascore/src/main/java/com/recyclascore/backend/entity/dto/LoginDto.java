package com.recyclascore.backend.entity.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String userName;
    private String email;
    private String password;
}
