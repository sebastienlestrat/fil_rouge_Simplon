package com.recyclascore.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "userrolerelation", schema = "mydb")
public class UserRoleRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}

