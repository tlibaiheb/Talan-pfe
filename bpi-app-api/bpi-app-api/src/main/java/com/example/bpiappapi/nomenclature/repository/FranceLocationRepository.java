package com.example.bpiappapi.nomenclature.repository;

import com.example.bpiappapi.nomenclature.model.FranceLocations;
import com.example.bpiappapi.nomenclature.model.TailleEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranceLocationRepository extends JpaRepository<FranceLocations,Long> {

    FranceLocations findByPostCode(String postCode);

}
