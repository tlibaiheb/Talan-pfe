package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.Naf;
import com.example.bpiappapi.nomenclature.service.NafCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/glossaries/naf-codes")
public class NafCodeController {

    @Autowired
    private NafCodeService nafCodeService;

    @GetMapping
    public List<Naf> getAllNafCodes() {
        return nafCodeService.getAllNafCodes();
    }
}
