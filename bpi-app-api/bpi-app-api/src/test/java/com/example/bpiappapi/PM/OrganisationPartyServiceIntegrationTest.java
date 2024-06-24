package com.example.bpiappapi.PM;

import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import com.example.bpiappapi.pm.reposotories.OrganisationPartyRepository;
import com.example.bpiappapi.pm.services.OrganisationPartyService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class OrganisationPartyServiceIntegrationTest {

    @Autowired
    private OrganisationPartyService organisationPartyService;

    @Autowired
    private OrganisationPartyRepository organisationPartyRepository;

    @Test
    void testCreateOrganisationParty() {
        // Création d'une nouvelle organisation
        OrganisationPartyRequest request = new OrganisationPartyRequest();
        // Appel  service pour créer une organisation
        OrganisationPartyRequest createdParty = organisationPartyService.createOrganisationParty(request);
        // Vérification si l'organisation créée n'est pas nulle
        assertNotNull(createdParty.getId());
    }




    @Test
    void testGetOrganisationPartyById() {
        OrganisationPartyRequest request = new OrganisationPartyRequest();
        OrganisationPartyRequest createdParty = organisationPartyService.createOrganisationParty(request);

        OrganisationPartyRequest retrievedParty = organisationPartyService.getOrganisationPartyById(createdParty.getId());

        assertNotNull(retrievedParty);
        assertEquals(createdParty.getId(), retrievedParty.getId());
    }



    @Test
    void testUpdateOrganisationParty() throws NotFoundException {
        //  un nouvel objet sans l'ajouter à la base de données
        OrganisationPartyRequest request = new OrganisationPartyRequest();

        //  l'objet dans la base de données en utilisant le service
        OrganisationPartyRequest createdParty = organisationPartyService.createOrganisationParty(request);

        // Récupéreration l'objet créé en utilisant son ID
        OrganisationPartyRequest retrievedParty = organisationPartyService.getOrganisationPartyById(createdParty.getId());

        // Modification les champs de l'objet récupéré
        retrievedParty.setLegalName("Updated Legal Name");

        // Mis à jour l'objet dans la base de données en utilisant le service
        OrganisationPartyRequest updatedParty = organisationPartyService.updateOrganisationParty(retrievedParty.getId(), retrievedParty);

        // Vérification que les modifications ont été appliquées avec succès
        assertEquals("Updated Legal Name", updatedParty.getLegalName());
    }

}
