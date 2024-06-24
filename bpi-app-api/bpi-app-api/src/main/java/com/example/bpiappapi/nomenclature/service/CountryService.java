package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CountryService {
    List<Country> getAllCountries();
    void saveAllCountries(List<Country> countries);
}
