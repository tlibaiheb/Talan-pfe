package com.example.bpiappapi;


import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import com.example.bpiappapi.pm.reposotories.OrganisationPartyRepository;
import com.example.bpiappapi.pm.services.OrganisationPartyServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

class OrganisationPartyServiceImpUnitaireTest {

    @Mock
    private OrganisationPartyRepository organisationPartyRepository;

    @InjectMocks
    private OrganisationPartyServiceImpl organisationPartyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

   @Test
    void testCreateOrganisationParty() {
        OrganisationPartyRequest request = new OrganisationPartyRequest();
        when(organisationPartyRepository.save(any())).thenReturn(request);

        OrganisationPartyRequest createdParty = organisationPartyService.createOrganisationParty(request);

        assertEquals(request, createdParty);
        verify(organisationPartyRepository, times(2)).save(any());
    }

    @Test
    void testDeleteOrganisationParty() {
        Long id = 1L;
        OrganisationPartyRequest existingParty = new OrganisationPartyRequest();
        when(organisationPartyRepository.findById(id)).thenReturn(Optional.of(existingParty));

        organisationPartyService.deleteOrganisationParty(id);

        verify(organisationPartyRepository, times(1)).delete(existingParty);
    }

    @Test
    void testGetOrganisationPartyById() {
        Long id = 1L;
        OrganisationPartyRequest expectedParty = new OrganisationPartyRequest();
        when(organisationPartyRepository.findById(id)).thenReturn(Optional.of(expectedParty));

        OrganisationPartyRequest retrievedParty = organisationPartyService.getOrganisationPartyById(id);

        assertEquals(expectedParty, retrievedParty);
    }

    @Test
    void testUpdateOrganisationParty() throws NotFoundException {
        Long id = 1L;
        OrganisationPartyRequest request = new OrganisationPartyRequest();
        OrganisationPartyRequest existingParty = new OrganisationPartyRequest();
        when(organisationPartyRepository.findById(id)).thenReturn(Optional.of(existingParty));
        when(organisationPartyRepository.save(any())).thenReturn(existingParty);

        OrganisationPartyRequest updatedParty = organisationPartyService.updateOrganisationParty(id, request);

        assertEquals(existingParty, updatedParty);
        verify(organisationPartyRepository, times(1)).save(existingParty);
    }

}
