package com.example.bpiappapi.pp.controllers;

import static org.mockito.Mockito.when;

import com.example.bpiappapi.pp.models.PersonParties;
import com.example.bpiappapi.pp.models.PhysicalContactPoint;
import com.example.bpiappapi.pp.services.PhysicalContactPointService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PhysicalContactPointServiceController.class})
@ExtendWith(SpringExtension.class)
class PhysicalContactPointServiceControllerTest {
    @MockBean
    private PhysicalContactPointService physicalContactPointService;

    @Autowired
    private PhysicalContactPointServiceController physicalContactPointServiceController;

    /**
     * Method under test:
     * {@link PhysicalContactPointServiceController#createAddress(Long, PhysicalContactPoint)}
     */
    @Test
    void testCreateAddress() throws Exception {
        // Arrange
        PhysicalContactPoint physicalContactPoint = new PhysicalContactPoint();
        physicalContactPoint.setAddress1("42 Main St");
        physicalContactPoint.setAddress2("42 Main St");
        physicalContactPoint.setAddress3("42 Main St");
        physicalContactPoint.setAddress4("42 Main St");
        physicalContactPoint.setComplementAdresse("Complement Adresse");
        physicalContactPoint.setCountryGId("GB");
        physicalContactPoint.setFrLocationGId("42");
        physicalContactPoint.setId(1L);
        physicalContactPoint.setInFrance(true);
        physicalContactPoint.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint.setNum("Num");
        physicalContactPoint.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint.setPersonParties(new PersonParties());
        physicalContactPoint.setPhysicalContactPointTypeGId(1);
        physicalContactPoint.setRegionOutFr("us-east-2");
        physicalContactPoint.setTypeDeVoie("Type De Voie");

        PersonParties personParties = new PersonParties();
        personParties.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties.setBirthName("Birth Name");
        personParties.setCountryOfBirthGId("GB");
        personParties.setCpNumber("42");
        personParties.setFirstNames("Jane");
        personParties.setFrPlaceOfBirthGId("42");
        personParties.setId(1L);
        personParties.setMainFirstName("Jane");
        personParties.setNamePrefixGId(1);
        personParties.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties.setPhysicalContactPoint(physicalContactPoint);
        personParties.setUseName("Use Name");

        PhysicalContactPoint physicalContactPoint2 = new PhysicalContactPoint();
        physicalContactPoint2.setAddress1("42 Main St");
        physicalContactPoint2.setAddress2("42 Main St");
        physicalContactPoint2.setAddress3("42 Main St");
        physicalContactPoint2.setAddress4("42 Main St");
        physicalContactPoint2.setComplementAdresse("Complement Adresse");
        physicalContactPoint2.setCountryGId("GB");
        physicalContactPoint2.setFrLocationGId("42");
        physicalContactPoint2.setId(1L);
        physicalContactPoint2.setInFrance(true);
        physicalContactPoint2.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint2.setNum("Num");
        physicalContactPoint2.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint2.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint2.setPersonParties(personParties);
        physicalContactPoint2.setPhysicalContactPointTypeGId(1);
        physicalContactPoint2.setRegionOutFr("us-east-2");
        physicalContactPoint2.setTypeDeVoie("Type De Voie");

        PersonParties personParties2 = new PersonParties();
        personParties2.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties2.setBirthName("Birth Name");
        personParties2.setCountryOfBirthGId("GB");
        personParties2.setCpNumber("42");
        personParties2.setFirstNames("Jane");
        personParties2.setFrPlaceOfBirthGId("42");
        personParties2.setId(1L);
        personParties2.setMainFirstName("Jane");
        personParties2.setNamePrefixGId(1);
        personParties2.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties2.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties2.setPhysicalContactPoint(physicalContactPoint2);
        personParties2.setUseName("Use Name");

        PhysicalContactPoint physicalContactPoint3 = new PhysicalContactPoint();
        physicalContactPoint3.setAddress1("42 Main St");
        physicalContactPoint3.setAddress2("42 Main St");
        physicalContactPoint3.setAddress3("42 Main St");
        physicalContactPoint3.setAddress4("42 Main St");
        physicalContactPoint3.setComplementAdresse("Complement Adresse");
        physicalContactPoint3.setCountryGId("GB");
        physicalContactPoint3.setFrLocationGId("42");
        physicalContactPoint3.setId(1L);
        physicalContactPoint3.setInFrance(true);
        physicalContactPoint3.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint3.setNum("Num");
        physicalContactPoint3.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint3.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint3.setPersonParties(personParties2);
        physicalContactPoint3.setPhysicalContactPointTypeGId(1);
        physicalContactPoint3.setRegionOutFr("us-east-2");
        physicalContactPoint3.setTypeDeVoie("Type De Voie");
        when(physicalContactPointService.createPhysicalContactPoint(Mockito.<Long>any(),
                Mockito.<PhysicalContactPoint>any())).thenReturn(physicalContactPoint3);

        PhysicalContactPoint physicalContactPoint4 = new PhysicalContactPoint();
        physicalContactPoint4.setAddress1("42 Main St");
        physicalContactPoint4.setAddress2("42 Main St");
        physicalContactPoint4.setAddress3("42 Main St");
        physicalContactPoint4.setAddress4("42 Main St");
        physicalContactPoint4.setComplementAdresse("Complement Adresse");
        physicalContactPoint4.setCountryGId("GB");
        physicalContactPoint4.setFrLocationGId("42");
        physicalContactPoint4.setId(1L);
        physicalContactPoint4.setInFrance(true);
        physicalContactPoint4.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint4.setNum("Num");
        physicalContactPoint4.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint4.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint4.setPersonParties(new PersonParties());
        physicalContactPoint4.setPhysicalContactPointTypeGId(1);
        physicalContactPoint4.setRegionOutFr("us-east-2");
        physicalContactPoint4.setTypeDeVoie("Type De Voie");

        PersonParties personParties3 = new PersonParties();
        personParties3.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties3.setBirthName("Birth Name");
        personParties3.setCountryOfBirthGId("GB");
        personParties3.setCpNumber("42");
        personParties3.setFirstNames("Jane");
        personParties3.setFrPlaceOfBirthGId("42");
        personParties3.setId(1L);
        personParties3.setMainFirstName("Jane");
        personParties3.setNamePrefixGId(1);
        personParties3.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties3.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties3.setPhysicalContactPoint(physicalContactPoint4);
        personParties3.setUseName("Use Name");

        PhysicalContactPoint physicalContactPoint5 = new PhysicalContactPoint();
        physicalContactPoint5.setAddress1("42 Main St");
        physicalContactPoint5.setAddress2("42 Main St");
        physicalContactPoint5.setAddress3("42 Main St");
        physicalContactPoint5.setAddress4("42 Main St");
        physicalContactPoint5.setComplementAdresse("Complement Adresse");
        physicalContactPoint5.setCountryGId("GB");
        physicalContactPoint5.setFrLocationGId("42");
        physicalContactPoint5.setId(1L);
        physicalContactPoint5.setInFrance(true);
        physicalContactPoint5.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint5.setNum("Num");
        physicalContactPoint5.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint5.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint5.setPersonParties(personParties3);
        physicalContactPoint5.setPhysicalContactPointTypeGId(1);
        physicalContactPoint5.setRegionOutFr("us-east-2");
        physicalContactPoint5.setTypeDeVoie("Type De Voie");

        PersonParties personParties4 = new PersonParties();
        personParties4.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties4.setBirthName("Birth Name");
        personParties4.setCountryOfBirthGId("GB");
        personParties4.setCpNumber("42");
        personParties4.setFirstNames("Jane");
        personParties4.setFrPlaceOfBirthGId("42");
        personParties4.setId(1L);
        personParties4.setMainFirstName("Jane");
        personParties4.setNamePrefixGId(1);
        personParties4.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties4.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties4.setPhysicalContactPoint(physicalContactPoint5);
        personParties4.setUseName("Use Name");

        PhysicalContactPoint physicalContactPoint6 = new PhysicalContactPoint();
        physicalContactPoint6.setAddress1("42 Main St");
        physicalContactPoint6.setAddress2("42 Main St");
        physicalContactPoint6.setAddress3("42 Main St");
        physicalContactPoint6.setAddress4("42 Main St");
        physicalContactPoint6.setComplementAdresse("Complement Adresse");
        physicalContactPoint6.setCountryGId("GB");
        physicalContactPoint6.setFrLocationGId("42");
        physicalContactPoint6.setId(1L);
        physicalContactPoint6.setInFrance(true);
        physicalContactPoint6.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint6.setNum("Num");
        physicalContactPoint6.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint6.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint6.setPersonParties(personParties4);
        physicalContactPoint6.setPhysicalContactPointTypeGId(1);
        physicalContactPoint6.setRegionOutFr("us-east-2");
        physicalContactPoint6.setTypeDeVoie("Type De Voie");
        String content = (new ObjectMapper()).writeValueAsString(physicalContactPoint6);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1//person-parties/{personPId}/physical-contact-points", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(physicalContactPointServiceController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"inFrance\":true,\"address1\":\"42 Main St\",\"address2\":\"42 Main St\",\"address3\":\"42 Main"
                                        + " St\",\"address4\":\"42 Main St\",\"frLocationGId\":\"42\",\"countryGId\":\"GB\",\"outOfFrTown\":\"Out Of Fr"
                                        + " Town\",\"outOfFrPostCode\":\"Out Of Fr Post Code\",\"physicalContactPointTypeGId\":1,\"num\":\"Num\",\"typeDeVoie"
                                        + "\":\"Type De Voie\",\"libelleDeLaVoie\":\"Libelle De La Voie\",\"regionOutFr\":\"us-east-2\",\"complementAdresse"
                                        + "\":\"Complement Adresse\"}"));
    }

    /**
     * Method under test:
     * {@link PhysicalContactPointServiceController#getAddressyById(Long)}
     */
    @Test
    void testGetAddressyById() throws Exception {
        // Arrange
        PhysicalContactPoint physicalContactPoint = new PhysicalContactPoint();
        physicalContactPoint.setAddress1("42 Main St");
        physicalContactPoint.setAddress2("42 Main St");
        physicalContactPoint.setAddress3("42 Main St");
        physicalContactPoint.setAddress4("42 Main St");
        physicalContactPoint.setComplementAdresse("Complement Adresse");
        physicalContactPoint.setCountryGId("GB");
        physicalContactPoint.setFrLocationGId("42");
        physicalContactPoint.setId(1L);
        physicalContactPoint.setInFrance(true);
        physicalContactPoint.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint.setNum("Num");
        physicalContactPoint.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint.setPersonParties(new PersonParties());
        physicalContactPoint.setPhysicalContactPointTypeGId(1);
        physicalContactPoint.setRegionOutFr("us-east-2");
        physicalContactPoint.setTypeDeVoie("Type De Voie");

        PersonParties personParties = new PersonParties();
        personParties.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties.setBirthName("Birth Name");
        personParties.setCountryOfBirthGId("GB");
        personParties.setCpNumber("42");
        personParties.setFirstNames("Jane");
        personParties.setFrPlaceOfBirthGId("42");
        personParties.setId(1L);
        personParties.setMainFirstName("Jane");
        personParties.setNamePrefixGId(1);
        personParties.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties.setPhysicalContactPoint(physicalContactPoint);
        personParties.setUseName("Use Name");

        PhysicalContactPoint physicalContactPoint2 = new PhysicalContactPoint();
        physicalContactPoint2.setAddress1("42 Main St");
        physicalContactPoint2.setAddress2("42 Main St");
        physicalContactPoint2.setAddress3("42 Main St");
        physicalContactPoint2.setAddress4("42 Main St");
        physicalContactPoint2.setComplementAdresse("Complement Adresse");
        physicalContactPoint2.setCountryGId("GB");
        physicalContactPoint2.setFrLocationGId("42");
        physicalContactPoint2.setId(1L);
        physicalContactPoint2.setInFrance(true);
        physicalContactPoint2.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint2.setNum("Num");
        physicalContactPoint2.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint2.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint2.setPersonParties(personParties);
        physicalContactPoint2.setPhysicalContactPointTypeGId(1);
        physicalContactPoint2.setRegionOutFr("us-east-2");
        physicalContactPoint2.setTypeDeVoie("Type De Voie");

        PersonParties personParties2 = new PersonParties();
        personParties2.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties2.setBirthName("Birth Name");
        personParties2.setCountryOfBirthGId("GB");
        personParties2.setCpNumber("42");
        personParties2.setFirstNames("Jane");
        personParties2.setFrPlaceOfBirthGId("42");
        personParties2.setId(1L);
        personParties2.setMainFirstName("Jane");
        personParties2.setNamePrefixGId(1);
        personParties2.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties2.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties2.setPhysicalContactPoint(physicalContactPoint2);
        personParties2.setUseName("Use Name");

        PhysicalContactPoint physicalContactPoint3 = new PhysicalContactPoint();
        physicalContactPoint3.setAddress1("42 Main St");
        physicalContactPoint3.setAddress2("42 Main St");
        physicalContactPoint3.setAddress3("42 Main St");
        physicalContactPoint3.setAddress4("42 Main St");
        physicalContactPoint3.setComplementAdresse("Complement Adresse");
        physicalContactPoint3.setCountryGId("GB");
        physicalContactPoint3.setFrLocationGId("42");
        physicalContactPoint3.setId(1L);
        physicalContactPoint3.setInFrance(true);
        physicalContactPoint3.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint3.setNum("Num");
        physicalContactPoint3.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint3.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint3.setPersonParties(personParties2);
        physicalContactPoint3.setPhysicalContactPointTypeGId(1);
        physicalContactPoint3.setRegionOutFr("us-east-2");
        physicalContactPoint3.setTypeDeVoie("Type De Voie");
        when(physicalContactPointService.getAdressById(Mockito.<Long>any())).thenReturn(physicalContactPoint3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1//person-parties/{personPId}/physical-contact-points/api/v1//person-parties/physical-contact"
                        + "-points/{id}", 1L, 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(physicalContactPointServiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"inFrance\":true,\"address1\":\"42 Main St\",\"address2\":\"42 Main St\",\"address3\":\"42 Main"
                                        + " St\",\"address4\":\"42 Main St\",\"frLocationGId\":\"42\",\"countryGId\":\"GB\",\"outOfFrTown\":\"Out Of Fr"
                                        + " Town\",\"outOfFrPostCode\":\"Out Of Fr Post Code\",\"physicalContactPointTypeGId\":1,\"num\":\"Num\",\"typeDeVoie"
                                        + "\":\"Type De Voie\",\"libelleDeLaVoie\":\"Libelle De La Voie\",\"regionOutFr\":\"us-east-2\",\"complementAdresse"
                                        + "\":\"Complement Adresse\"}"));
    }
}
