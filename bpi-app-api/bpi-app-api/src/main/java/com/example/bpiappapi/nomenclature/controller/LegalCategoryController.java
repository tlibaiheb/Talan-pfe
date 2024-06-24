package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.LegalCategories;
import com.example.bpiappapi.nomenclature.service.LegalCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/glossaries/legal-categories")
public class LegalCategoryController {
    @Autowired
    private LegalCategoryService legalCategoryService;

    @GetMapping
    public List<LegalCategories> getAllLegalCategories() {
        return legalCategoryService.getAllLegalCategories();
    }
}
