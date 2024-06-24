package com.example.bpiappapi.nomenclature.repository;

import com.example.bpiappapi.nomenclature.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
