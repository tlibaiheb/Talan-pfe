package com.example.bpiappapi.pm.reposotories;

import com.example.bpiappapi.pm.models.AddressForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressForm, Long> {
}

