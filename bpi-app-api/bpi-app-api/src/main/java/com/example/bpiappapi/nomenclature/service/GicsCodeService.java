package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.GicsCodes;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GicsCodeService {
    List<GicsCodes> getAllGicsCodes();
    void saveAllGicsCodes(List<GicsCodes> gicsCodes);

}
