package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.TailleEntreprise;
import com.example.bpiappapi.nomenclature.repository.TailleEntrepriseRepository;
import com.example.bpiappapi.nomenclature.service.TailleEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TailleEntrepriseServiceImpl implements TailleEntrepriseService {
    private final TailleEntrepriseRepository tailleEntrepriseRepository;

    @Autowired
    public TailleEntrepriseServiceImpl(TailleEntrepriseRepository tailleEntrepriseRepository) {
        this.tailleEntrepriseRepository = tailleEntrepriseRepository;
    }

    @Override
    public List<TailleEntreprise> getAllTailleEntreprises() {
        return tailleEntrepriseRepository.findAll();
    }



    @Override
    public void saveAllTailleEntreprises(List<TailleEntreprise> tailleEntreprises) {
        tailleEntrepriseRepository.saveAll(tailleEntreprises);
    }
}
