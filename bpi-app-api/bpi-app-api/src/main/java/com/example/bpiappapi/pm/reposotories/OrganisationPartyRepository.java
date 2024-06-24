package com.example.bpiappapi.pm.reposotories;

import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationPartyRepository extends JpaRepository <OrganisationPartyRequest, Long> {

    boolean existsBySiren(String siren);
    OrganisationPartyRequest findBySiren (String siren);


}

