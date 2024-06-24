package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.NamePrefixes;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface NamePrefixService {
    List<NamePrefixes> getAllNamePrefixes();

    void saveAllNamePrefixes(List<NamePrefixes> namePrefixes);
}
