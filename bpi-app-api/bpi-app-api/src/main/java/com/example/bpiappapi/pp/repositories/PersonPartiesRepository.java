package com.example.bpiappapi.pp.repositories;

import com.example.bpiappapi.pp.models.PersonParties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonPartiesRepository extends JpaRepository<PersonParties,Long> {
    List<PersonParties> findByCpNumber(String cpNumber);
    List<PersonParties> findAllByBirthNameIgnoreCaseStartingWith(String birthName);
    List<PersonParties> findByMainFirstName(String mainFirstName);


    List<PersonParties> findByIdTiers(String idTiers);

    List<PersonParties> findAllByMainFirstNameIgnoreCaseStartingWith(String mainFirstName);

}
