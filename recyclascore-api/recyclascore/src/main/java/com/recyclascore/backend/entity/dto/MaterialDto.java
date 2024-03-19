package com.recyclascore.backend.entity.dto;

import lombok.Data;

@Data
public class MaterialDto {
    String id;
    String name;
    String materialType;
    boolean isRecyclable;
    int recyclingScore;
    double recyclingBudget;
}
