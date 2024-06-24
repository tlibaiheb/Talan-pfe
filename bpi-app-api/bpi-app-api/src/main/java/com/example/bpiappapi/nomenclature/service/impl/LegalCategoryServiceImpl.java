package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.LegalCategories;
import com.example.bpiappapi.nomenclature.repository.LegalCategoryRepository;
import com.example.bpiappapi.nomenclature.service.LegalCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalCategoryServiceImpl implements LegalCategoryService {
    private final LegalCategoryRepository legalCategoryRepository;

    @Autowired
    public LegalCategoryServiceImpl(LegalCategoryRepository legalCategoryRepository) {
        this.legalCategoryRepository = legalCategoryRepository;
    }

    @Override
    public List<LegalCategories> getAllLegalCategories() {
        return legalCategoryRepository.findAll();
    }

    @Override
    public void saveAllLegalCategories(List<LegalCategories> legalCategories) {
        legalCategoryRepository.saveAll(legalCategories);
    }
}
