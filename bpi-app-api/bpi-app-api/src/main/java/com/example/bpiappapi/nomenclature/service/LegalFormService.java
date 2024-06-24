package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.LegalForms;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LegalFormService {
    List<LegalForms> getAllLegalForms();

    void saveAllLegalForms(List<LegalForms> legalForms);
}
