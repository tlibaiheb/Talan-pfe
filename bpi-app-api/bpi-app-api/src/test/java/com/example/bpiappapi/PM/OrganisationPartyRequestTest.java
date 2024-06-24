package com.example.bpiappapi.PM;

import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

 class OrganisationPartyRequestTest {

    @Test
      void testOrganisationPartyRequestBuilderAndGettersSetters() {
        Date creationDate = new Date();
        Date registrationDate = new Date();

        OrganisationPartyRequest organisationPartyRequest = OrganisationPartyRequest.builder()
                .id(1L)
                .CP("12345")
                .tradingName("Trading Name")
                .creationDate(creationDate)
                .europeanOrganisationPartySizeGId(100)
                .idTiers("1234567890")
                .gicsCodeGId("GICS123")
                .legalCategoryGId("LegalCat123")
                .legalName("Legal Name")
                .siren("123456789")
                .missingSIRENSIRETJustificationGId(10)
                .nafCodeGId("NAF123")
                .registrationDate(registrationDate)
                .shareCapital("100000")
                .build();

        assertNotNull(organisationPartyRequest);
        assertEquals(1L, organisationPartyRequest.getId());
        assertEquals("12345", organisationPartyRequest.getCP());
        assertEquals("Trading Name", organisationPartyRequest.getTradingName());
        assertEquals(creationDate, organisationPartyRequest.getCreationDate());
        assertEquals(100, organisationPartyRequest.getEuropeanOrganisationPartySizeGId());
        assertEquals("1234567890", organisationPartyRequest.getIdTiers());
        assertEquals("GICS123", organisationPartyRequest.getGicsCodeGId());
        assertEquals("LegalCat123", organisationPartyRequest.getLegalCategoryGId());
        assertEquals("Legal Name", organisationPartyRequest.getLegalName());
        assertEquals("123456789", organisationPartyRequest.getSiren());
        assertEquals(10, organisationPartyRequest.getMissingSIRENSIRETJustificationGId());
        assertEquals("NAF123", organisationPartyRequest.getNafCodeGId());
        assertEquals(registrationDate, organisationPartyRequest.getRegistrationDate());
        assertEquals("100000", organisationPartyRequest.getShareCapital());
    }

    @Test
      void testGenerateIdTiers() {
        OrganisationPartyRequest organisationPartyRequest = new OrganisationPartyRequest();
        String idTiers = organisationPartyRequest.generateIdTiers();
        assertNotNull(idTiers);
        assertEquals(10, idTiers.length());
        assertTrue(idTiers.matches("\\d{10}"));
    }




}
