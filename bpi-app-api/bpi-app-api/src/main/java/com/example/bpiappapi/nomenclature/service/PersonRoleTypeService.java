package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.PersonRoleTypes;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PersonRoleTypeService {
    List<PersonRoleTypes> getAllPersonRoleTypes();

    void saveAllPersonRoleTypes(List<PersonRoleTypes> personRoleTypes);
}
