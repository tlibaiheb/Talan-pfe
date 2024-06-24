package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.FranceLocations;
import com.example.bpiappapi.nomenclature.repository.FranceLocationRepository;
import com.example.bpiappapi.nomenclature.service.FranceLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranceLocationServiceImpl  implements FranceLocationService {
    private final FranceLocationRepository franceLocationRepository;

    @Autowired
    public FranceLocationServiceImpl(FranceLocationRepository franceLocationRepository) {
        this.franceLocationRepository = franceLocationRepository;
    }

    @Override
    public List<FranceLocations> getAllLocations() {
        return franceLocationRepository.findAll();
    }

    @Override
    public void saveAllLocations(List<FranceLocations> locations) {
        franceLocationRepository.saveAll(locations);
    }
}
