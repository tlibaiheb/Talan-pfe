package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.NamePrefixes;
import com.example.bpiappapi.nomenclature.repository.NamePrefixRepository;
import com.example.bpiappapi.nomenclature.service.NamePrefixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamePrefixServiceImpl implements NamePrefixService {
    private final NamePrefixRepository namePrefixRepository;

    @Autowired
    public NamePrefixServiceImpl(NamePrefixRepository namePrefixRepository) {
        this.namePrefixRepository = namePrefixRepository;
    }

    @Override
    public List<NamePrefixes> getAllNamePrefixes() {
        return namePrefixRepository.findAll();
    }



    @Override
    public void saveAllNamePrefixes(List<NamePrefixes> namePrefixes) {
namePrefixRepository.saveAll(namePrefixes);
    }
}
