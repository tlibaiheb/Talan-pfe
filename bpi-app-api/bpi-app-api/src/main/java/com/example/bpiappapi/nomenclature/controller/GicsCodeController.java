package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.GicsCodes;
import com.example.bpiappapi.nomenclature.service.GicsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/glossaries/gics-codes")
public class GicsCodeController {

    @Autowired
    private GicsCodeService gicsCodeService;

    @GetMapping
    public List<GicsCodes> getAllGicsCodes() {
        return gicsCodeService.getAllGicsCodes();
    }
}
