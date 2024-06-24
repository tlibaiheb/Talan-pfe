package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.Naf;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface NafCodeService {
    List<Naf> getAllNafCodes();

    void saveAllNafCodes(List<Naf> nafCodes);
}

