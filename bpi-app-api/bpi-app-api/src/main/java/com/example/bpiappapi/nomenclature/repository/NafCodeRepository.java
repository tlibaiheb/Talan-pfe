package com.example.bpiappapi.nomenclature.repository;

import com.example.bpiappapi.nomenclature.model.Naf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NafCodeRepository extends JpaRepository<Naf,Long> {

    Naf findByCode(String code);
}
