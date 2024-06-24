package com.example.bpiappapi.nomenclature.repository;

import com.example.bpiappapi.nomenclature.model.PersonRoleTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRoleTypeRepository extends JpaRepository<PersonRoleTypes,Long> {
}
