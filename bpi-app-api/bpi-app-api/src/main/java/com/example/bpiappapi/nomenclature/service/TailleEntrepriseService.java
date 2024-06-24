package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.TailleEntreprise;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TailleEntrepriseService {
    List<TailleEntreprise> getAllTailleEntreprises();

    void saveAllTailleEntreprises(List<TailleEntreprise> tailleEntreprises);
}
