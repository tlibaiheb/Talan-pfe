package com.example.bpiappapi.pp.services;

import com.example.bpiappapi.pp.models.PersonParties;

import java.util.List;

public interface PersonPartiesService {
    PersonParties createPersonParties(PersonParties personParties);
    PersonParties getPersonPartiesById(Long id);
    PersonParties updatePersonParties(Long id, PersonParties personParties);

    public List<PersonParties> getAllPersonParties() ;

    List<PersonParties> getPersonPartiesByCpNumber(String cpNumber);

    List<PersonParties> getPersonPartiesByBirthName(String birthName);


    List<PersonParties> getPersonPartiesByMainFirstName(String mainFirstName);
    List<PersonParties> getPersonPartiesByIdTiers(String idTiers);







}
