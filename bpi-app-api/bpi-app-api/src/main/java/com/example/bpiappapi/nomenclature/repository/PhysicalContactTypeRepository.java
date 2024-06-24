package com.example.bpiappapi.nomenclature.repository;

import com.example.bpiappapi.nomenclature.model.PhysicalContactPointType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalContactTypeRepository extends JpaRepository<PhysicalContactPointType,Long> {
}
