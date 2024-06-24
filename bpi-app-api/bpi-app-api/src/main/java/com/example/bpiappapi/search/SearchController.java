package com.example.bpiappapi.search;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/api/v1/search")
    public String searchByNomComplet(@RequestParam String nomComplet) {
        // Appeler le service pour rechercher par nom complet et obtenir le SIREN
        String siren = searchService.searchByNomComplet(nomComplet);

        return siren;
    }
}

