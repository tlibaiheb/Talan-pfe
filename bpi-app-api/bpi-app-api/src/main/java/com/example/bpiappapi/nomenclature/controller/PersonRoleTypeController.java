package com.example.bpiappapi.nomenclature.controller;

import com.example.bpiappapi.nomenclature.model.PersonRoleTypes;
import com.example.bpiappapi.nomenclature.service.PersonRoleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/glossaries/person-role-types")
public class PersonRoleTypeController {
    @Autowired
    private PersonRoleTypeService personRoleTypeService;

    @GetMapping
    public List<PersonRoleTypes> getAllPersonRoleTypes() {
        return personRoleTypeService.getAllPersonRoleTypes();
    }
}
