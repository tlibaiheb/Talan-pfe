package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.PhysicalContactPointType;
import com.example.bpiappapi.nomenclature.service.PhysicalContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/glossaries/physical-contact-point-types")
public class PhysicalContactTypeController {
    @Autowired
    private PhysicalContactTypeService physicalContactTypeService;

    @GetMapping
    public List<PhysicalContactPointType> getAllPhysicalContactType() {
        return physicalContactTypeService.getAllPhysicalContactTypes();
    }
}
