package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.TailleEntreprise;
import com.example.bpiappapi.nomenclature.service.TailleEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/glossaries/european-organisation-party-sizes")
public class TailleEntrepriseController {
    @Autowired
    private TailleEntrepriseService tailleEntrepriseService;

    @GetMapping
    public List<TailleEntreprise> getAllTailleEntreprises() {
        return tailleEntrepriseService.getAllTailleEntreprises();
    }
}
