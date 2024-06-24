package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.Naf;
import com.example.bpiappapi.nomenclature.repository.NafCodeRepository;
import com.example.bpiappapi.nomenclature.service.NafCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NafCodeServiceImpl implements NafCodeService {
    private final NafCodeRepository nafCodeRepository;

    @Autowired
    public NafCodeServiceImpl(NafCodeRepository nafCodeRepository) {
        this.nafCodeRepository = nafCodeRepository;
    }

    @Override
    public List<Naf> getAllNafCodes() {
        return nafCodeRepository.findAll();
    }



    @Override
    public void saveAllNafCodes(List<Naf> nafCodes) {
        nafCodeRepository.saveAll(nafCodes);

    }
}
