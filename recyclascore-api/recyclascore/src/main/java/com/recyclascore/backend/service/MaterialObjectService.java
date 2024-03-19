package com.recyclascore.backend.service;

import com.recyclascore.backend.entity.Material;
import com.recyclascore.backend.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialObjectService {

    @Autowired
    MaterialRepository materialRepository;

    public List<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    public Material getMaterial(Long id) {
        return materialRepository.findById(id).orElseThrow();
    }
}
