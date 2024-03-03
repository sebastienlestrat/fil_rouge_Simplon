package com.recyclascore.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public Role (RoleEnum roleName) {
        this.role = roleName;
    }
    public String getRole() {
        return role.toString();
    }
}
