package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.PhysicalContactPointType;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PhysicalContactTypeService {
    List<PhysicalContactPointType> getAllPhysicalContactTypes();

    void saveAllPhysicalContactPointTypes(List<PhysicalContactPointType> physicalContactPointTypes);
}

