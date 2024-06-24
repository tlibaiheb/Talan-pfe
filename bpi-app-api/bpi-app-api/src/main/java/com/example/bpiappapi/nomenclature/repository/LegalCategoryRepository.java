package com.example.bpiappapi.nomenclature.repository;

import com.example.bpiappapi.nomenclature.model.LegalCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalCategoryRepository extends JpaRepository<LegalCategories,Long> {
}
