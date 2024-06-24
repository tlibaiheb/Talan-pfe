package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.LegalCategories;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LegalCategoryService {
    List<LegalCategories> getAllLegalCategories();
    void saveAllLegalCategories(List<LegalCategories> legalCategories);

}
