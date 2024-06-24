package com.example.bpiappapi.PM;

import com.example.bpiappapi.pm.models.OrganisationPartyIdentification;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

  class OrganisationPartyIdentificationTest {

    @Test
      void testOrganisationPartyIdentificationBuilderAndGettersSetters() {
        OrganisationPartyRequest organisationPartyRequest = new OrganisationPartyRequest();
        Date startingDate = new Date();
        Date closingDate = new Date();

        OrganisationPartyIdentification organisationPartyIdentification = OrganisationPartyIdentification.builder()
                .id(1L)
                .organisationPartyIdentificationTypeGId(123)
                .organisationPartyIdentificationValue("Value123")
                .organisationPartyRequest(organisationPartyRequest)
                .startingDate(startingDate)
                .closingDate(closingDate)
                .build();

        assertNotNull(organisationPartyIdentification);
        assertEquals(1L, organisationPartyIdentification.getId());
        assertEquals(123, organisationPartyIdentification.getOrganisationPartyIdentificationTypeGId());
        assertEquals("Value123", organisationPartyIdentification.getOrganisationPartyIdentificationValue());
        assertEquals(organisationPartyRequest, organisationPartyIdentification.getOrganisationPartyRequest());
        assertEquals(startingDate, organisationPartyIdentification.getStartingDate());
        assertEquals(closingDate, organisationPartyIdentification.getClosingDate());
    }

    @Test
      void testSetters() {
        OrganisationPartyIdentification organisationPartyIdentification = new OrganisationPartyIdentification();

        organisationPartyIdentification.setId(1L);
        assertEquals(1L, organisationPartyIdentification.getId());

        organisationPartyIdentification.setOrganisationPartyIdentificationTypeGId(123);
        assertEquals(123, organisationPartyIdentification.getOrganisationPartyIdentificationTypeGId());

        organisationPartyIdentification.setOrganisationPartyIdentificationValue("Value123");
        assertEquals("Value123", organisationPartyIdentification.getOrganisationPartyIdentificationValue());

        OrganisationPartyRequest organisationPartyRequest = new OrganisationPartyRequest();
        organisationPartyIdentification.setOrganisationPartyRequest(organisationPartyRequest);
        assertEquals(organisationPartyRequest, organisationPartyIdentification.getOrganisationPartyRequest());

        Date startingDate = new Date();
        organisationPartyIdentification.setStartingDate(startingDate);
        assertEquals(startingDate, organisationPartyIdentification.getStartingDate());

        Date closingDate = new Date();
        organisationPartyIdentification.setClosingDate(closingDate);
        assertEquals(closingDate, organisationPartyIdentification.getClosingDate());
    }



    @Test
    void testEqualsAndHashCode() {
      OrganisationPartyRequest organisationPartyRequest = new OrganisationPartyRequest();
      Date startingDate = new Date();
      Date closingDate = new Date();

      OrganisationPartyIdentification opi1 = OrganisationPartyIdentification.builder()
              .id(1L)
              .organisationPartyIdentificationTypeGId(123)
              .organisationPartyIdentificationValue("Value123")
              .organisationPartyRequest(organisationPartyRequest)
              .startingDate(startingDate)
              .closingDate(closingDate)
              .build();

      OrganisationPartyIdentification opi2 = OrganisationPartyIdentification.builder()
              .id(1L)
              .organisationPartyIdentificationTypeGId(123)
              .organisationPartyIdentificationValue("Value123")
              .organisationPartyRequest(organisationPartyRequest)
              .startingDate(startingDate)
              .closingDate(closingDate)
              .build();

      assertEquals(opi1, opi2);
      assertEquals(opi1.hashCode(), opi2.hashCode());

      opi2.setId(2L);
      assertNotEquals(opi1, opi2);
      assertNotEquals(opi1.hashCode(), opi2.hashCode());
    }

    @Test
    void testToString() {
      OrganisationPartyRequest organisationPartyRequest = new OrganisationPartyRequest();
      Date startingDate = new Date();
      Date closingDate = new Date();

      OrganisationPartyIdentification organisationPartyIdentification = OrganisationPartyIdentification.builder()
              .id(1L)
              .organisationPartyIdentificationTypeGId(123)
              .organisationPartyIdentificationValue("Value123")
              .organisationPartyRequest(organisationPartyRequest)
              .startingDate(startingDate)
              .closingDate(closingDate)
              .build();

      String expectedString = "OrganisationPartyIdentification(id=1, organisationPartyIdentificationTypeGId=123, organisationPartyIdentificationValue=Value123, startingDate=" + startingDate + ", closingDate=" + closingDate + ", organisationPartyRequest=" + organisationPartyRequest + ")";
      assertEquals(expectedString, organisationPartyIdentification.toString());
    }
}
