package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.NamePrefixes;
import com.example.bpiappapi.nomenclature.service.NamePrefixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/glossaries/name-prefixes")
public class NamePrefixController {
    @Autowired
    private NamePrefixService namePrefixService;

    @GetMapping
    public List<NamePrefixes> getAllNamePrefixes() {
        return namePrefixService.getAllNamePrefixes();
    }
}
