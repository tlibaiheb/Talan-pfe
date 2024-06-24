package com.example.bpiappapi.PM;

import com.example.bpiappapi.pm.controllers.OrganisationPartyController;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import com.example.bpiappapi.pm.services.OrganisationPartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OrganisationPartyControllerTest {

    @Mock
    private OrganisationPartyService organisationPartyService;

    @InjectMocks
    private OrganisationPartyController organisationPartyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrganisationParty() {
        OrganisationPartyRequest request = new OrganisationPartyRequest();
        when(organisationPartyService.createOrganisationParty(request)).thenReturn(request);

        ResponseEntity<OrganisationPartyRequest> response = organisationPartyController.createOrganisationParty(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(request);
    }

    @Test
    void testFiltrerListe() {
        String selectedOption = "option";
        String searchValue = "value";
        List<OrganisationPartyRequest> filteredParties = new ArrayList<>();
        when(organisationPartyService.filtrerListe(selectedOption, searchValue)).thenReturn(filteredParties);

        ResponseEntity<List<OrganisationPartyRequest>> response = organisationPartyController.filtrerListe(selectedOption, searchValue);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(filteredParties);
    }

    // Écrivez des tests similaires pour les autres méthodes du contrôleur
}
