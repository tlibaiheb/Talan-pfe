package com.example.bpiappapi.PM;



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

import java.util.ArrayList;
import java.util.List;
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
    void testCreateOrganisationParty_ExceptionThrown() {
        OrganisationPartyRequest request = new OrganisationPartyRequest();
        when(organisationPartyRepository.save(any())).thenThrow(new RuntimeException("Failed to save organisation party"));

        assertThrows(RuntimeException.class, () -> {
            organisationPartyService.createOrganisationParty(request);
        });
        verify(organisationPartyRepository, times(1)).save(any());
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
    void testDeleteOrganisationParty_IdNotFound() {
        Long id = 1L;
        when(organisationPartyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            organisationPartyService.deleteOrganisationParty(id);
        });
        verify(organisationPartyRepository, times(0)).delete(any());
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
    void testGetOrganisationPartyById_IdNotFound() {
        Long id = 1L;
        when(organisationPartyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            organisationPartyService.getOrganisationPartyById(id);
        });
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


    @Test
    void testUpdateOrganisationParty_ExceptionThrown() throws NotFoundException {
        Long id = 1L;
        OrganisationPartyRequest request = new OrganisationPartyRequest();
        OrganisationPartyRequest existingParty = new OrganisationPartyRequest();
        when(organisationPartyRepository.findById(id)).thenReturn(Optional.of(existingParty));
        when(organisationPartyRepository.save(any())).thenThrow(new RuntimeException("Failed to update organisation party"));

        assertThrows(RuntimeException.class, () -> {
            organisationPartyService.updateOrganisationParty(id, request);
        });
        verify(organisationPartyRepository, times(1)).save(existingParty);
    }


    @Test
    void testFiltrerListe_Success() {
        // Créer une liste de données de test
        List<OrganisationPartyRequest> testData = new ArrayList<>();
        OrganisationPartyRequest party1 = new OrganisationPartyRequest();
        party1.setIdTiers("123456");
        testData.add(party1);
        OrganisationPartyRequest party2 = new OrganisationPartyRequest();
        party2.setIdTiers("789012");
        testData.add(party2);

        // Configurer le comportement du repository mock
        when(organisationPartyRepository.findAll()).thenReturn(testData);

        // Tester la méthode filtrerListe pour le cas de succès
        List<OrganisationPartyRequest> filteredList = organisationPartyService.filtrerListe("idTiers", "123");

        // Vérifier que la liste filtrée contient le bon élément
        assertEquals(1, filteredList.size());
        assertEquals("123456", filteredList.get(0).getIdTiers());
    }


    @Test
    void testExistsBySiren_Success() {
        String siren = "123456789";
        // Configurer le comportement du repository mock
        when(organisationPartyRepository.existsBySiren(siren)).thenReturn(true);

        // Tester la méthode existsBySiren pour le cas de succès
        boolean exists = organisationPartyService.existsBySiren(siren);

        // Vérifier que le SIREN existe
        assertTrue(exists);
    }

    @Test
    void testGetBySiren_Success() {
        String siren = "123456789";
        OrganisationPartyRequest expectedParty = new OrganisationPartyRequest();
        expectedParty.setSiren(siren);
        // Configurer le comportement du repository mock
        when(organisationPartyRepository.findBySiren(siren)).thenReturn(expectedParty);

        // Tester la méthode getBySiren pour le cas de succès
        OrganisationPartyRequest retrievedParty = organisationPartyService.getBySiren(siren);

        // Vérifier que le bon objet est retourné
        assertEquals(expectedParty, retrievedParty);
    }


    @Test
    void testFiltrerListe_Error() {
        // Configurer le comportement du repository mock pour lancer une exception lors de l'appel à findAll()
        when(organisationPartyRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // Tester la méthode filtrerListe pour le cas d'erreur
        assertThrows(RuntimeException.class, () -> {
            organisationPartyService.filtrerListe("idTiers", "123");
        });
    }


    @Test
    void testExistsBySiren_Error() {
        String siren = "123456789";
        // Configurer le comportement du repository mock pour renvoyer false et lancer une exception
        when(organisationPartyRepository.existsBySiren(siren)).thenThrow(new RuntimeException("Database error"));

        // Tester la méthode existsBySiren pour le cas d'erreur
        assertThrows(RuntimeException.class, () -> {
            organisationPartyService.existsBySiren(siren);
        });
    }

    @Test
    void testGetBySiren_Error() {
        String siren = "123456789";
        when(organisationPartyRepository.findBySiren(siren)).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> {
            organisationPartyService.getBySiren(siren);
        });
    }


    @Test
    void testGetAllOrganisationParties_Error() {
        // Configurer le comportement du repository mock pour lancer une exception lors de l'appel à findAll()
        when(organisationPartyRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // Tester la méthode getAllOrganisationParties pour le cas d'erreur
        assertThrows(RuntimeException.class, () -> {
            organisationPartyService.getAllOrganisationParties();
        });
    }


    @Test
    void testGetAllOrganisationParties_Success() {
        List<OrganisationPartyRequest> expectedParties = new ArrayList<>();
        expectedParties.add(new OrganisationPartyRequest());
        expectedParties.add(new OrganisationPartyRequest());

        // Configurer le comportement du repository mock pour retourner la liste fictive lors de l'appel à findAll()
        when(organisationPartyRepository.findAll()).thenReturn(expectedParties);

        // Appeler la méthode getAllOrganisationParties du service
        List<OrganisationPartyRequest> actualParties = organisationPartyService.getAllOrganisationParties();

        // Vérifier que la liste retournée par le service est la même que celle retournée par le repository
        assertEquals(expectedParties.size(), actualParties.size());
        for (int i = 0; i < expectedParties.size(); i++) {
            assertEquals(expectedParties.get(i), actualParties.get(i));
        }
    }


    @Test
    void testFiltrerListe_BySIREN_Success() {
        // Créer une liste de parties d'organisation fictive
        List<OrganisationPartyRequest> organisationParties = new ArrayList<>();
        organisationParties.add(new OrganisationPartyRequest());
        organisationParties.add(new OrganisationPartyRequest());
        organisationParties.get(0).setSiren("123456789"); // Ajouter un SIREN à la première organisation
        organisationParties.get(1).setSiren("987654321"); // Ajouter un SIREN à la deuxième organisation

        // Configurer le comportement du repository mock pour retourner la liste fictive lors de l'appel à findAll()
        when(organisationPartyRepository.findAll()).thenReturn(organisationParties);

        // Appeler la méthode filtrerListe avec l'option "SIREN" et la valeur de recherche "123"
        List<OrganisationPartyRequest> filteredParties = organisationPartyService.filtrerListe("SIREN", "123");

        // Vérifier que la liste filtrée ne contient que la première organisation avec le SIREN correspondant
        assertEquals(1, filteredParties.size());
        assertEquals("123456789", filteredParties.get(0).getSiren());
    }

    @Test
    void testFiltrerListe_ByRaisonSociale_Success() {
        // Créer une liste de parties d'organisation fictive
        List<OrganisationPartyRequest> organisationParties = new ArrayList<>();
        organisationParties.add(new OrganisationPartyRequest());
        organisationParties.add(new OrganisationPartyRequest());
        organisationParties.get(0).setLegalName("ACME Inc."); // Ajouter un nom légal à la première organisation
        organisationParties.get(1).setLegalName("XYZ Company"); // Ajouter un nom légal à la deuxième organisation

        // Configurer le comportement du repository mock pour retourner la liste fictive lors de l'appel à findAll()
        when(organisationPartyRepository.findAll()).thenReturn(organisationParties);

        // Appeler la méthode filtrerListe avec l'option "RaisonSocial" et la valeur de recherche "ACME"
        List<OrganisationPartyRequest> filteredParties = organisationPartyService.filtrerListe("RaisonSocial", "ACME");

        // Vérifier que la liste filtrée ne contient que la première organisation avec le nom légal correspondant
        assertEquals(1, filteredParties.size());
        assertEquals("ACME Inc.", filteredParties.get(0).getLegalName());
    }

    @Test
    void testFiltrerListe_ByNuméroCP_Success() {
        // Créer une liste de parties d'organisation fictive
        List<OrganisationPartyRequest> organisationParties = new ArrayList<>();
        OrganisationPartyRequest party1 = new OrganisationPartyRequest();
        OrganisationPartyRequest party2 = new OrganisationPartyRequest();
        party1.setCP("12345"); // Ajouter un numéro CP à la première organisation
        party2.setCP("54321"); // Ajouter un numéro CP à la deuxième organisation
        organisationParties.add(party1);
        organisationParties.add(party2);

        // Configurer le comportement du repository mock pour retourner la liste fictive lors de l'appel à findAll()
        when(organisationPartyRepository.findAll()).thenReturn(organisationParties);

        // Appeler la méthode filtrerListe avec l'option "NuméroCP" et la valeur de recherche "123"
        List<OrganisationPartyRequest> filteredParties = organisationPartyService.filtrerListe("NuméroCP", "123");

        // Vérifier que la liste filtrée ne contient que la première organisation avec le numéro CP correspondant
        assertEquals(1, filteredParties.size());
        assertEquals("12345", filteredParties.get(0).getCP());
    }



    @Test
    void testFiltrerListe_ByNuméroCP_Error() {
        // Simuler une exception lors de l'appel à findAll() dans le repository
        when(organisationPartyRepository.findAll()).thenThrow(new RuntimeException("Error fetching organisation parties"));

        assertThrows(RuntimeException.class, () -> organisationPartyService.filtrerListe("NuméroCP", "123"));
    }

    @Test
    void testCreateOrganisationParty_Success() {
        // Créer une demande d'organisation fictive
        OrganisationPartyRequest request = new OrganisationPartyRequest();

        // Configurer le comportement du repository mock pour retourner la demande créée lors de l'appel à save()
        when(organisationPartyRepository.save(any())).thenReturn(request);

        // Appeler la méthode createOrganisationParty avec la demande fictive
        OrganisationPartyRequest createdParty = organisationPartyService.createOrganisationParty(request);

        // Vérifier que la demande retournée est la même que celle créée
        assertEquals(request, createdParty);
        verify(organisationPartyRepository, times(2)).save(any());
    }







}
