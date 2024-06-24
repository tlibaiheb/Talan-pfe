package com.example.bpiappapi.pp.repositories;

import com.example.bpiappapi.pp.models.PhysicalContactPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicalContactPointRepository extends JpaRepository<PhysicalContactPoint,Long> {
}
