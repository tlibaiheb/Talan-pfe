package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.MissingSiretSirenJustifications;
import com.example.bpiappapi.nomenclature.service.MissingJustificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/glossaries/missing-siren-siret-justifications")
public class MissingJustificationsController {
    @Autowired
    private MissingJustificationsService missingJustificationsService;

    @GetMapping
    public List<MissingSiretSirenJustifications> getAllMissing() {
        return missingJustificationsService.getAllMissing();
    }
}
