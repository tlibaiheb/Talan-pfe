package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.PersonRoleTypes;
import com.example.bpiappapi.nomenclature.repository.PersonRoleTypeRepository;
import com.example.bpiappapi.nomenclature.service.PersonRoleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonRoleTypeServiceImpl implements PersonRoleTypeService {
    private final PersonRoleTypeRepository personRoleTypeRepository;

    @Autowired
    public PersonRoleTypeServiceImpl(PersonRoleTypeRepository personRoleTypeRepository) {
        this.personRoleTypeRepository = personRoleTypeRepository;
    }

    @Override
    public List<PersonRoleTypes> getAllPersonRoleTypes() {
        return personRoleTypeRepository.findAll();
    }



    @Override
    public void saveAllPersonRoleTypes(List<PersonRoleTypes> personRoleTypes) {
         personRoleTypeRepository.saveAll(personRoleTypes);

    }
}
