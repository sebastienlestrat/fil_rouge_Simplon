package com.recyclascore.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String materialType;
    @Column(name = "is_recyclable") // Specify the column name explicitly
    private boolean isRecyclable;
    private int recyclingScore;
    private double recyclingBudget;
}
