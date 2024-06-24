package com.example.bpiappapi.pm.reposotories;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;


import com.example.bpiappapi.pm.models.OrganisationPartyIdentification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganistaionIdentif extends JpaRepository<OrganisationPartyIdentification, Long> {


    List<OrganisationPartyIdentification> getAllByOrganisationPartyRequest_Id(Long id);
    void deleteByOrganisationPartyRequestId(Long id);
}

