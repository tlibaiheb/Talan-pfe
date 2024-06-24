package com.example.bpiappapi.nomenclature.repository;

import com.example.bpiappapi.nomenclature.model.TailleEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TailleEntrepriseRepository extends JpaRepository<TailleEntreprise,Long> {
    TailleEntreprise findByLabel(String label);

}
