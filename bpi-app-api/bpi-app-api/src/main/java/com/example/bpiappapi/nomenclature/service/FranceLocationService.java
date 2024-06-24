package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.FranceLocations;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FranceLocationService {
    List<FranceLocations> getAllLocations();
    void saveAllLocations(List<FranceLocations> locations);

}
