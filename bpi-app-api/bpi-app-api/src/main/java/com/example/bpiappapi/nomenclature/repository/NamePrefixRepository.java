package com.example.bpiappapi.nomenclature.repository;

import com.example.bpiappapi.nomenclature.model.NamePrefixes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NamePrefixRepository extends JpaRepository<NamePrefixes,Long> {
}
