package com.recyclascore.backend.controller;

import com.recyclascore.backend.entity.Material;
import com.recyclascore.backend.service.MaterialObjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/material")
public class MaterialObjectController {

    @Autowired
    MaterialObjectService materialObjectService;

    @GetMapping(value = "/all")
    public List<Material> getAllMaterials() {
        return  materialObjectService.findAllMaterials();
    }

    @GetMapping(value="/{id}")
    public Material getMaterial(@RequestParam Long id) {
        return materialObjectService.getMaterial(id);
    }
}
