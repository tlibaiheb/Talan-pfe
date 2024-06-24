package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.LegalForms;
import com.example.bpiappapi.nomenclature.repository.LegalFormRepository;
import com.example.bpiappapi.nomenclature.service.LegalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalFormServiceImpl implements LegalFormService {
    private final LegalFormRepository legalFormRepository;

    @Autowired
    public LegalFormServiceImpl(LegalFormRepository legalFormRepository) {
        this.legalFormRepository = legalFormRepository;
    }

    @Override
    public List<LegalForms> getAllLegalForms() {
        return legalFormRepository.findAll();
    }




    @Override
    public void saveAllLegalForms(List<LegalForms> legalForms) {
        legalFormRepository.saveAll(legalForms);

    }
}
