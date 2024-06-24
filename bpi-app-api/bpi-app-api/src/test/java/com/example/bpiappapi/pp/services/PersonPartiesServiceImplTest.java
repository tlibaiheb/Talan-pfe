package com.example.bpiappapi.pp.services;

import static  org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.bpiappapi.pp.models.PersonParties;
import com.example.bpiappapi.pp.models.PhysicalContactPoint;
import com.example.bpiappapi.pp.repositories.PersonPartiesRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PersonPartiesServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PersonPartiesServiceImplTest {
    @MockBean
    private PersonPartiesRepository personPartiesRepository;

    @Autowired
    private PersonPartiesServiceImpl personPartiesServiceImpl;

    /**
     * Method under test:
     * {@link PersonPartiesServiceImpl#createPersonParties(PersonParties)}
     */
    @Test
    void testCreatePersonParties() {
        // Arrange
        PersonParties personParties = new PersonParties();
        personParties.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties.setBirthName("Birth Name");
        personParties.setIdTiers("1111");
        personParties.setCountryOfBirthGId("GB");
        personParties.setCpNumber("42");
        personParties.setFirstNames("Jane");
        personParties.setFrPlaceOfBirthGId("42");
        personParties.setId(1L);
        personParties.setMainFirstName("Jane");
        personParties.setNamePrefixGId(1);
        personParties.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties.setPhysicalContactPoint(new PhysicalContactPoint());
        personParties.setUseName("Use Name");

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
        physicalContactPoint.setPersonParties(personParties);
        physicalContactPoint.setPhysicalContactPointTypeGId(1);
        physicalContactPoint.setRegionOutFr("us-east-2");
        physicalContactPoint.setTypeDeVoie("Type De Voie");

        PersonParties personParties2 = new PersonParties();
        personParties2.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties2.setBirthName("Birth Name");
        personParties.setIdTiers("1111");
        personParties2.setCountryOfBirthGId("GB");
        personParties2.setCpNumber("42");
        personParties2.setFirstNames("Jane");
        personParties2.setFrPlaceOfBirthGId("42");
        personParties2.setId(1L);
        personParties2.setMainFirstName("Jane");
        personParties2.setNamePrefixGId(1);
        personParties2.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties2.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties2.setPhysicalContactPoint(physicalContactPoint);
        personParties2.setUseName("Use Name");

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
        physicalContactPoint2.setPersonParties(personParties2);
        physicalContactPoint2.setPhysicalContactPointTypeGId(1);
        physicalContactPoint2.setRegionOutFr("us-east-2");
        physicalContactPoint2.setTypeDeVoie("Type De Voie");

        PersonParties personParties3 = new PersonParties();
        personParties3.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties3.setBirthName("Birth Name");
        personParties3.setCountryOfBirthGId("GB");
        personParties.setIdTiers("1111");
        personParties3.setCpNumber("42");
        personParties3.setFirstNames("Jane");
        personParties3.setFrPlaceOfBirthGId("42");
        personParties3.setId(1L);
        personParties3.setMainFirstName("Jane");
        personParties3.setNamePrefixGId(1);
        personParties3.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties3.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties3.setPhysicalContactPoint(physicalContactPoint2);
        personParties3.setUseName("Use Name");
        when(personPartiesRepository.save(Mockito.<PersonParties>any())).thenReturn(personParties3);

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
        physicalContactPoint3.setPersonParties(new PersonParties());
        physicalContactPoint3.setPhysicalContactPointTypeGId(1);
        physicalContactPoint3.setRegionOutFr("us-east-2");
        physicalContactPoint3.setTypeDeVoie("Type De Voie");

        PersonParties personParties4 = new PersonParties();
        personParties4.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties4.setBirthName("Birth Name");
        personParties4.setCountryOfBirthGId("GB");
        personParties.setIdTiers("1111");
        personParties4.setCpNumber("42");
        personParties4.setFirstNames("Jane");
        personParties4.setFrPlaceOfBirthGId("42");
        personParties4.setId(1L);
        personParties4.setMainFirstName("Jane");
        personParties4.setNamePrefixGId(1);
        personParties4.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties4.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties4.setPhysicalContactPoint(physicalContactPoint3);
        personParties4.setUseName("Use Name");

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
        physicalContactPoint4.setPersonParties(personParties4);
        physicalContactPoint4.setPhysicalContactPointTypeGId(1);
        physicalContactPoint4.setRegionOutFr("us-east-2");
        physicalContactPoint4.setTypeDeVoie("Type De Voie");

        PersonParties personParties5 = new PersonParties();
        personParties5.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties5.setBirthName("Birth Name");
        personParties5.setCountryOfBirthGId("GB");
        personParties5.setCpNumber("42");
        personParties5.setFirstNames("Jane");
        personParties.setIdTiers("1111");
        personParties5.setFrPlaceOfBirthGId("42");
        personParties5.setId(1L);
        personParties5.setMainFirstName("Jane");
        personParties5.setNamePrefixGId(1);
        personParties5.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties5.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties5.setPhysicalContactPoint(physicalContactPoint4);
        personParties5.setUseName("Use Name");

        // Act
        PersonParties actualCreatePersonPartiesResult = personPartiesServiceImpl.createPersonParties(personParties5);

        // Assert
        verify(personPartiesRepository).save(isA(PersonParties.class));

        assertSame(personParties3, actualCreatePersonPartiesResult);
    }

    /**
     * Method under test:
     * {@link PersonPartiesServiceImpl#getPersonPartiesById(Long)}
     */
    @Test
    void testGetPersonPartiesById() {
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
        Optional<PersonParties> ofResult = Optional.of(personParties2);
        when(personPartiesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        PersonParties actualPersonPartiesById = personPartiesServiceImpl.getPersonPartiesById(1L);

        // Assert
        verify(personPartiesRepository).findById(eq(1L));
        assertSame(personParties2, actualPersonPartiesById);
    }

    /**
     * Method under test:
     * {@link PersonPartiesServiceImpl#updatePersonParties(Long, PersonParties)}
     */
    @Test
    void testUpdatePersonParties() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        PersonParties personParties3 = mock(PersonParties.class);
        when(personParties3.getPhysicalContactPoint()).thenReturn(physicalContactPoint3);
        doNothing().when(personParties3).setBirthDate(Mockito.<Date>any());
        doNothing().when(personParties3).setBirthName(Mockito.<String>any());
        doNothing().when(personParties3).setCountryOfBirthGId(Mockito.<String>any());
        doNothing().when(personParties3).setCpNumber(Mockito.<String>any());
        doNothing().when(personParties3).setFirstNames(Mockito.<String>any());
        doNothing().when(personParties3).setFrPlaceOfBirthGId(Mockito.<String>any());
        doNothing().when(personParties3).setMainFirstName(Mockito.<String>any());
        doNothing().when(personParties3).setNamePrefixGId(anyInt());
        doNothing().when(personParties3).setOutOfFrBirthPostCode(Mockito.<String>any());
        doNothing().when(personParties3).setOutOfFrPlaceOfBirth(Mockito.<String>any());
        doNothing().when(personParties3).setUseName(Mockito.<String>any());
        Optional<PersonParties> ofResult = Optional.of(personParties3);

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
        personParties4.setPhysicalContactPoint(new PhysicalContactPoint());
        personParties4.setUseName("Use Name");

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
        physicalContactPoint4.setPersonParties(personParties4);
        physicalContactPoint4.setPhysicalContactPointTypeGId(1);
        physicalContactPoint4.setRegionOutFr("us-east-2");
        physicalContactPoint4.setTypeDeVoie("Type De Voie");

        PersonParties personParties5 = new PersonParties();
        personParties5.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties5.setBirthName("Birth Name");
        personParties5.setCountryOfBirthGId("GB");
        personParties5.setCpNumber("42");
        personParties5.setFirstNames("Jane");
        personParties5.setFrPlaceOfBirthGId("42");
        personParties5.setId(1L);
        personParties5.setMainFirstName("Jane");
        personParties5.setNamePrefixGId(1);
        personParties5.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties5.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties5.setPhysicalContactPoint(physicalContactPoint4);
        personParties5.setUseName("Use Name");

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
        physicalContactPoint5.setPersonParties(personParties5);
        physicalContactPoint5.setPhysicalContactPointTypeGId(1);
        physicalContactPoint5.setRegionOutFr("us-east-2");
        physicalContactPoint5.setTypeDeVoie("Type De Voie");

        PersonParties personParties6 = new PersonParties();
        personParties6.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties6.setBirthName("Birth Name");
        personParties6.setCountryOfBirthGId("GB");
        personParties6.setCpNumber("42");
        personParties6.setFirstNames("Jane");
        personParties6.setFrPlaceOfBirthGId("42");
        personParties6.setId(1L);
        personParties6.setMainFirstName("Jane");
        personParties6.setNamePrefixGId(1);
        personParties6.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties6.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties6.setPhysicalContactPoint(physicalContactPoint5);
        personParties6.setUseName("Use Name");
        PersonPartiesRepository personPartiesRepository = mock(PersonPartiesRepository.class);
        when(personPartiesRepository.save(Mockito.<PersonParties>any())).thenReturn(personParties6);
        when(personPartiesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        PersonPartiesServiceImpl personPartiesServiceImpl = new PersonPartiesServiceImpl(personPartiesRepository);

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
        physicalContactPoint6.setPersonParties(new PersonParties());
        physicalContactPoint6.setPhysicalContactPointTypeGId(1);
        physicalContactPoint6.setRegionOutFr("us-east-2");
        physicalContactPoint6.setTypeDeVoie("Type De Voie");

        PersonParties personParties7 = new PersonParties();
        personParties7.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties7.setBirthName("Birth Name");
        personParties7.setCountryOfBirthGId("GB");
        personParties7.setCpNumber("42");
        personParties7.setFirstNames("Jane");
        personParties7.setFrPlaceOfBirthGId("42");
        personParties7.setId(1L);
        personParties7.setMainFirstName("Jane");
        personParties7.setNamePrefixGId(1);
        personParties7.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties7.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties7.setPhysicalContactPoint(physicalContactPoint6);
        personParties7.setUseName("Use Name");

        PhysicalContactPoint physicalContactPoint7 = new PhysicalContactPoint();
        physicalContactPoint7.setAddress1("42 Main St");
        physicalContactPoint7.setAddress2("42 Main St");
        physicalContactPoint7.setAddress3("42 Main St");
        physicalContactPoint7.setAddress4("42 Main St");
        physicalContactPoint7.setComplementAdresse("Complement Adresse");
        physicalContactPoint7.setCountryGId("GB");
        physicalContactPoint7.setFrLocationGId("42");
        physicalContactPoint7.setId(1L);
        physicalContactPoint7.setInFrance(true);
        physicalContactPoint7.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint7.setNum("Num");
        physicalContactPoint7.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint7.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint7.setPersonParties(personParties7);
        physicalContactPoint7.setPhysicalContactPointTypeGId(1);
        physicalContactPoint7.setRegionOutFr("us-east-2");
        physicalContactPoint7.setTypeDeVoie("Type De Voie");

        PersonParties personParties8 = new PersonParties();
        personParties8.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties8.setBirthName("Birth Name");
        personParties8.setCountryOfBirthGId("GB");
        personParties8.setCpNumber("42");
        personParties8.setFirstNames("Jane");
        personParties8.setFrPlaceOfBirthGId("42");
        personParties8.setId(1L);
        personParties8.setMainFirstName("Jane");
        personParties8.setNamePrefixGId(1);
        personParties8.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties8.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties8.setPhysicalContactPoint(physicalContactPoint7);
        personParties8.setUseName("Use Name");

        PhysicalContactPoint physicalContactPoint8 = new PhysicalContactPoint();
        physicalContactPoint8.setAddress1("42 Main St");
        physicalContactPoint8.setAddress2("42 Main St");
        physicalContactPoint8.setAddress3("42 Main St");
        physicalContactPoint8.setAddress4("42 Main St");
        physicalContactPoint8.setComplementAdresse("Complement Adresse");
        physicalContactPoint8.setCountryGId("GB");
        physicalContactPoint8.setFrLocationGId("42");
        physicalContactPoint8.setId(1L);
        physicalContactPoint8.setInFrance(true);
        physicalContactPoint8.setLibelleDeLaVoie("Libelle De La Voie");
        physicalContactPoint8.setNum("Num");
        physicalContactPoint8.setOutOfFrPostCode("Out Of Fr Post Code");
        physicalContactPoint8.setOutOfFrTown("Out Of Fr Town");
        physicalContactPoint8.setPersonParties(personParties8);
        physicalContactPoint8.setPhysicalContactPointTypeGId(1);
        physicalContactPoint8.setRegionOutFr("us-east-2");
        physicalContactPoint8.setTypeDeVoie("Type De Voie");
        PersonParties personParties9 = mock(PersonParties.class);
        when(personParties9.getPhysicalContactPoint()).thenReturn(physicalContactPoint8);
        when(personParties9.getNamePrefixGId()).thenReturn(1);
        when(personParties9.getBirthName()).thenReturn("Birth Name");
        when(personParties9.getCountryOfBirthGId()).thenReturn("GB");
        when(personParties9.getCpNumber()).thenReturn("42");
        when(personParties9.getFirstNames()).thenReturn("Jane");
        when(personParties9.getFrPlaceOfBirthGId()).thenReturn("42");
        when(personParties9.getMainFirstName()).thenReturn("Jane");
        when(personParties9.getOutOfFrBirthPostCode()).thenReturn("Out Of Fr Birth Post Code");
        when(personParties9.getOutOfFrPlaceOfBirth()).thenReturn("Out Of Fr Place Of Birth");
        when(personParties9.getUseName()).thenReturn("Use Name");
        when(personParties9.getBirthDate())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Act
        PersonParties actualUpdatePersonPartiesResult = personPartiesServiceImpl.updatePersonParties(1L, personParties9);

        // Assert
        verify(personParties9).getBirthDate();
        verify(personParties9).getBirthName();
        verify(personParties9).getCountryOfBirthGId();
        verify(personParties9).getCpNumber();
        verify(personParties9).getFirstNames();
        verify(personParties9).getFrPlaceOfBirthGId();
        verify(personParties9).getMainFirstName();
        verify(personParties9).getNamePrefixGId();
        verify(personParties9).getOutOfFrBirthPostCode();
        verify(personParties9).getOutOfFrPlaceOfBirth();
        verify(personParties3).getPhysicalContactPoint();
        verify(personParties9, atLeast(1)).getPhysicalContactPoint();
        verify(personParties9).getUseName();
        verify(personParties3).setBirthDate(isA(Date.class));
        verify(personParties3).setBirthName(eq("Birth Name"));
        verify(personParties3).setCountryOfBirthGId(eq("GB"));
        verify(personParties3).setCpNumber(eq("42"));
        verify(personParties3).setFirstNames(eq("Jane"));
        verify(personParties3).setFrPlaceOfBirthGId(eq("42"));
        verify(personParties3).setMainFirstName(eq("Jane"));
        verify(personParties3).setNamePrefixGId(eq(1));
        verify(personParties3).setOutOfFrBirthPostCode(eq("Out Of Fr Birth Post Code"));
        verify(personParties3).setOutOfFrPlaceOfBirth(eq("Out Of Fr Place Of Birth"));
        verify(personParties3).setUseName(eq("Use Name"));
        verify(personPartiesRepository).findById(eq(1L));
        verify(personPartiesRepository).save(isA(PersonParties.class));
        assertSame(personParties6, actualUpdatePersonPartiesResult);
    }

    /**
     * Method under test:
     * {@link PersonPartiesServiceImpl#updatePersonParties(Long, PersonParties)}
     */
    @Test
    void testUpdatePersonParties2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        PersonParties personParties = mock(PersonParties.class);
        when(personParties.getPhysicalContactPoint()).thenThrow(new RuntimeException("foo"));
        doNothing().when(personParties).setBirthDate(Mockito.<Date>any());
        doNothing().when(personParties).setBirthName(Mockito.<String>any());
        doNothing().when(personParties).setCountryOfBirthGId(Mockito.<String>any());
        doNothing().when(personParties).setCpNumber(Mockito.<String>any());
        doNothing().when(personParties).setFirstNames(Mockito.<String>any());
        doNothing().when(personParties).setFrPlaceOfBirthGId(Mockito.<String>any());
        doNothing().when(personParties).setMainFirstName(Mockito.<String>any());
        doNothing().when(personParties).setNamePrefixGId(anyInt());
        doNothing().when(personParties).setOutOfFrBirthPostCode(Mockito.<String>any());
        doNothing().when(personParties).setOutOfFrPlaceOfBirth(Mockito.<String>any());
        doNothing().when(personParties).setUseName(Mockito.<String>any());
        Optional<PersonParties> ofResult = Optional.of(personParties);
        PersonPartiesRepository personPartiesRepository = mock(PersonPartiesRepository.class);
        when(personPartiesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        PersonPartiesServiceImpl personPartiesServiceImpl = new PersonPartiesServiceImpl(personPartiesRepository);

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
        personParties2.setPhysicalContactPoint(physicalContactPoint);
        personParties2.setUseName("Use Name");

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
        physicalContactPoint2.setPersonParties(personParties2);
        physicalContactPoint2.setPhysicalContactPointTypeGId(1);
        physicalContactPoint2.setRegionOutFr("us-east-2");
        physicalContactPoint2.setTypeDeVoie("Type De Voie");

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
        personParties3.setPhysicalContactPoint(physicalContactPoint2);
        personParties3.setUseName("Use Name");

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
        physicalContactPoint3.setPersonParties(personParties3);
        physicalContactPoint3.setPhysicalContactPointTypeGId(1);
        physicalContactPoint3.setRegionOutFr("us-east-2");
        physicalContactPoint3.setTypeDeVoie("Type De Voie");
        PersonParties personParties4 = mock(PersonParties.class);
        when(personParties4.getPhysicalContactPoint()).thenReturn(physicalContactPoint3);
        when(personParties4.getNamePrefixGId()).thenReturn(1);
        when(personParties4.getBirthName()).thenReturn("Birth Name");
        when(personParties4.getCountryOfBirthGId()).thenReturn("GB");
        when(personParties4.getCpNumber()).thenReturn("42");
        when(personParties4.getFirstNames()).thenReturn("Jane");
        when(personParties4.getFrPlaceOfBirthGId()).thenReturn("42");
        when(personParties4.getMainFirstName()).thenReturn("Jane");
        when(personParties4.getOutOfFrBirthPostCode()).thenReturn("Out Of Fr Birth Post Code");
        when(personParties4.getOutOfFrPlaceOfBirth()).thenReturn("Out Of Fr Place Of Birth");
        when(personParties4.getUseName()).thenReturn("Use Name");
        when(personParties4.getBirthDate())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> personPartiesServiceImpl.updatePersonParties(1L, personParties4));
        verify(personParties4).getBirthDate();
        verify(personParties4).getBirthName();
        verify(personParties4).getCountryOfBirthGId();
        verify(personParties4).getCpNumber();
        verify(personParties4).getFirstNames();
        verify(personParties4).getFrPlaceOfBirthGId();
        verify(personParties4).getMainFirstName();
        verify(personParties4).getNamePrefixGId();
        verify(personParties4).getOutOfFrBirthPostCode();
        verify(personParties4).getOutOfFrPlaceOfBirth();
        verify(personParties).getPhysicalContactPoint();
        verify(personParties4).getPhysicalContactPoint();
        verify(personParties4).getUseName();
        verify(personParties).setBirthDate(isA(Date.class));
        verify(personParties).setBirthName(eq("Birth Name"));
        verify(personParties).setCountryOfBirthGId(eq("GB"));
        verify(personParties).setCpNumber(eq("42"));
        verify(personParties).setFirstNames(eq("Jane"));
        verify(personParties).setFrPlaceOfBirthGId(eq("42"));
        verify(personParties).setMainFirstName(eq("Jane"));
        verify(personParties).setNamePrefixGId(eq(1));
        verify(personParties).setOutOfFrBirthPostCode(eq("Out Of Fr Birth Post Code"));
        verify(personParties).setOutOfFrPlaceOfBirth(eq("Out Of Fr Place Of Birth"));
        verify(personParties).setUseName(eq("Use Name"));
        verify(personPartiesRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link PersonPartiesServiceImpl#updatePersonParties(Long, PersonParties)}
     */
    @Test
    void testUpdatePersonParties3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        PersonParties personParties = mock(PersonParties.class);
        doNothing().when(personParties).setBirthDate(Mockito.<Date>any());
        doNothing().when(personParties).setBirthName(Mockito.<String>any());
        doNothing().when(personParties).setCountryOfBirthGId(Mockito.<String>any());
        doNothing().when(personParties).setCpNumber(Mockito.<String>any());
        doNothing().when(personParties).setFirstNames(Mockito.<String>any());
        doNothing().when(personParties).setFrPlaceOfBirthGId(Mockito.<String>any());
        doNothing().when(personParties).setMainFirstName(Mockito.<String>any());
        doNothing().when(personParties).setNamePrefixGId(anyInt());
        doNothing().when(personParties).setOutOfFrBirthPostCode(Mockito.<String>any());
        doNothing().when(personParties).setOutOfFrPlaceOfBirth(Mockito.<String>any());
        doNothing().when(personParties).setUseName(Mockito.<String>any());
        Optional<PersonParties> ofResult = Optional.of(personParties);

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
        personParties2.setPhysicalContactPoint(new PhysicalContactPoint());
        personParties2.setUseName("Use Name");

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
        physicalContactPoint.setPersonParties(personParties2);
        physicalContactPoint.setPhysicalContactPointTypeGId(1);
        physicalContactPoint.setRegionOutFr("us-east-2");
        physicalContactPoint.setTypeDeVoie("Type De Voie");

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
        personParties3.setPhysicalContactPoint(physicalContactPoint);
        personParties3.setUseName("Use Name");

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
        physicalContactPoint2.setPersonParties(personParties3);
        physicalContactPoint2.setPhysicalContactPointTypeGId(1);
        physicalContactPoint2.setRegionOutFr("us-east-2");
        physicalContactPoint2.setTypeDeVoie("Type De Voie");

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
        personParties4.setPhysicalContactPoint(physicalContactPoint2);
        personParties4.setUseName("Use Name");
        PersonPartiesRepository personPartiesRepository = mock(PersonPartiesRepository.class);
        when(personPartiesRepository.save(Mockito.<PersonParties>any())).thenReturn(personParties4);
        when(personPartiesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        PersonPartiesServiceImpl personPartiesServiceImpl = new PersonPartiesServiceImpl(personPartiesRepository);

        PersonParties personParties5 = new PersonParties();
        personParties5.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        personParties5.setBirthName("Birth Name");
        personParties5.setCountryOfBirthGId("GB");
        personParties5.setCpNumber("42");
        personParties5.setFirstNames("Jane");
        personParties5.setFrPlaceOfBirthGId("42");
        personParties5.setId(1L);
        personParties5.setMainFirstName("Jane");
        personParties5.setNamePrefixGId(1);
        personParties5.setOutOfFrBirthPostCode("Out Of Fr Birth Post Code");
        personParties5.setOutOfFrPlaceOfBirth("Out Of Fr Place Of Birth");
        personParties5.setUseName("Use Name");
        personParties5.setPhysicalContactPoint(null);

        // Act
        PersonParties actualUpdatePersonPartiesResult = personPartiesServiceImpl.updatePersonParties(1L, personParties5);

        // Assert
        verify(personParties).setBirthDate(isA(Date.class));
        verify(personParties).setBirthName(eq("Birth Name"));
        verify(personParties).setCountryOfBirthGId(eq("GB"));
        verify(personParties).setCpNumber(eq("42"));
        verify(personParties).setFirstNames(eq("Jane"));
        verify(personParties).setFrPlaceOfBirthGId(eq("42"));
        verify(personParties).setMainFirstName(eq("Jane"));
        verify(personParties).setNamePrefixGId(eq(1));
        verify(personParties).setOutOfFrBirthPostCode(eq("Out Of Fr Birth Post Code"));
        verify(personParties).setOutOfFrPlaceOfBirth(eq("Out Of Fr Place Of Birth"));
        verify(personParties).setUseName(eq("Use Name"));
        verify(personPartiesRepository).findById(eq(1L));
        verify(personPartiesRepository).save(isA(PersonParties.class));
        assertSame(personParties4, actualUpdatePersonPartiesResult);
    }

    /**
     * Method under test:
     * {@link PersonPartiesServiceImpl#updatePersonParties(Long, PersonParties)}
     */
    @Test
    void testUpdatePersonParties4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        PersonParties personParties = mock(PersonParties.class);
        doThrow(new RuntimeException("foo")).when(personParties).setNamePrefixGId(anyInt());
        Optional<PersonParties> ofResult = Optional.of(personParties);
        PersonPartiesRepository personPartiesRepository = mock(PersonPartiesRepository.class);
        when(personPartiesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        PersonPartiesServiceImpl personPartiesServiceImpl = new PersonPartiesServiceImpl(personPartiesRepository);

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
        personParties2.setUseName("Use Name");
        personParties2.setPhysicalContactPoint(null);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> personPartiesServiceImpl.updatePersonParties(1L, personParties2));
        verify(personParties).setNamePrefixGId(eq(1));
        verify(personPartiesRepository).findById(eq(1L));
    }
}
