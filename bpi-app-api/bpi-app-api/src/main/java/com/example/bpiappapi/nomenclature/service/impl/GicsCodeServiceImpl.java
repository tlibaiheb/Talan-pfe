package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.GicsCodes;
import com.example.bpiappapi.nomenclature.repository.GicsCodeRepository;
import com.example.bpiappapi.nomenclature.service.GicsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GicsCodeServiceImpl implements GicsCodeService {
    private final GicsCodeRepository gicsCodeRepository;

    @Autowired
    public GicsCodeServiceImpl(GicsCodeRepository gicsCodeRepository) {
        this.gicsCodeRepository = gicsCodeRepository;
    }

    @Override
    public List<GicsCodes> getAllGicsCodes() {
        return gicsCodeRepository.findAll();
    }

    @Override
    public void saveAllGicsCodes(List<GicsCodes> gicsCodes) {
        gicsCodeRepository.saveAll(gicsCodes);
    }
}
