package com.example.bpiappapi.pm.services;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import javassist.NotFoundException;

import java.util.List;

public interface OrganisationPartyService {
    OrganisationPartyRequest createOrganisationParty(OrganisationPartyRequest request);
    OrganisationPartyRequest updateOrganisationParty(Long id, OrganisationPartyRequest request) throws NotFoundException;
    void deleteOrganisationParty(Long id);
    OrganisationPartyRequest getOrganisationPartyById(Long id);

    public List <OrganisationPartyRequest> getAllOrganisationParties() ;
    public List<OrganisationPartyRequest> filtrerListe(String selectedOption, String searchValue) ;

    public boolean existsBySiren(String siren);

    public OrganisationPartyRequest getBySiren(String siren) ;


    }

