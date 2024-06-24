package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.PhysicalContactPointType;
import com.example.bpiappapi.nomenclature.repository.PhysicalContactTypeRepository;
import com.example.bpiappapi.nomenclature.service.PhysicalContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhysicalContactTypeServiceImpl implements PhysicalContactTypeService {
    private final PhysicalContactTypeRepository physicalContactTypeRepository;

    @Autowired
    public PhysicalContactTypeServiceImpl(PhysicalContactTypeRepository physicalContactTypeRepository) {
        this.physicalContactTypeRepository = physicalContactTypeRepository;
    }

    @Override
    public List<PhysicalContactPointType> getAllPhysicalContactTypes() {
        return physicalContactTypeRepository.findAll();
    }



    @Override
    public void saveAllPhysicalContactPointTypes(List<PhysicalContactPointType> physicalContactPointTypes) {
physicalContactTypeRepository.saveAll(physicalContactPointTypes);
    }
}
