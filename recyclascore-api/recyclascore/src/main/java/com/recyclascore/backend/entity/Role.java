package com.recyclascore.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "role", schema = "mydb")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    public Role (RoleEnum roleName) {
        this.roleName = roleName;
    }
    public String getRoleName() {
        return roleName.toString();
    }
}
