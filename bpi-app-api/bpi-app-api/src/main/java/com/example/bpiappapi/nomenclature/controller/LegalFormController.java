package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.LegalForms;
import com.example.bpiappapi.nomenclature.service.LegalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/glossaries/legal-forms")
public class LegalFormController {

    @Autowired
    private LegalFormService legalFormService;

    @GetMapping
    public List<LegalForms> getAllLegalForms() {
        return legalFormService.getAllLegalForms();
    }
}
