package com.recyclascore.backend.service;

import com.recyclascore.backend.entity.Material;
import com.recyclascore.backend.repository.MaterialObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialObjectService {

    @Autowired
    MaterialObjectRepository materialObjectRepository;

    public List<Material> findAllMaterials() {
        return materialObjectRepository.findAll();
    }

    public Material getMaterial(Long id) {
        return materialObjectRepository.findById(id).orElseThrow();
    }
}
