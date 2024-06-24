package com.example.bpiappapi.nomenclature.repository;

import com.example.bpiappapi.nomenclature.model.MissingSiretSirenJustifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingJustificationsRepository extends JpaRepository<MissingSiretSirenJustifications,Long> {
}
