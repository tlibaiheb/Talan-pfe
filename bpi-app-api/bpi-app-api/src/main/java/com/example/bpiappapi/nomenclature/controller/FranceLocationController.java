package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.FranceLocations;
import com.example.bpiappapi.nomenclature.service.FranceLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/glossaries/france-locations")
public class FranceLocationController {
    @Autowired
    private FranceLocationService franceLocationService;

    @GetMapping
    public List<FranceLocations> getAllLocations() {
        return franceLocationService.getAllLocations();
    }
}
