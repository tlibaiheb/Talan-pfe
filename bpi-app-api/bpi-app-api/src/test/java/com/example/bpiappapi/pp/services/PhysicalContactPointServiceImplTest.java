package com.example.bpiappapi.pp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.bpiappapi.pp.models.PersonParties;
import com.example.bpiappapi.pp.models.PhysicalContactPoint;
import com.example.bpiappapi.pp.repositories.PersonPartiesRepository;
import com.example.bpiappapi.pp.repositories.PhysicalContactPointRepository;

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

@ContextConfiguration(classes = {PhysicalContactPointServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PhysicalContactPointServiceImplTest {
    @MockBean
    private PersonPartiesRepository personPartiesRepository;

    @MockBean
    private PhysicalContactPointRepository physicalContactPointRepository;

    @Autowired
    private PhysicalContactPointServiceImpl physicalContactPointServiceImpl;

    /**
     * Method under test:
     * {@link PhysicalContactPointServiceImpl#createPhysicalContactPoint(Long, PhysicalContactPoint)}
     */
    @Test
    void testCreatePhysicalContactPoint() {
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
        when(physicalContactPointRepository.save(Mockito.<PhysicalContactPoint>any())).thenReturn(physicalContactPoint3);

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
        Optional<PersonParties> ofResult = Optional.of(personParties4);
        when(personPartiesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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
        personParties5.setPhysicalContactPoint(new PhysicalContactPoint());
        personParties5.setUseName("Use Name");

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
        physicalContactPoint6.setPersonParties(personParties5);
        physicalContactPoint6.setPhysicalContactPointTypeGId(1);
        physicalContactPoint6.setRegionOutFr("us-east-2");
        physicalContactPoint6.setTypeDeVoie("Type De Voie");

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
        personParties6.setPhysicalContactPoint(physicalContactPoint6);
        personParties6.setUseName("Use Name");

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
        physicalContactPoint7.setPersonParties(personParties6);
        physicalContactPoint7.setPhysicalContactPointTypeGId(1);
        physicalContactPoint7.setRegionOutFr("us-east-2");
        physicalContactPoint7.setTypeDeVoie("Type De Voie");

        // Act
        PhysicalContactPoint actualCreatePhysicalContactPointResult = physicalContactPointServiceImpl
                .createPhysicalContactPoint(1L, physicalContactPoint7);

        // Assert
        verify(personPartiesRepository).findById(eq(1L));
        verify(physicalContactPointRepository).save(isA(PhysicalContactPoint.class));
        assertEquals("42", physicalContactPoint7.getFrLocationGId());
        assertEquals("Birth Name", physicalContactPoint7.getPersonParties().getBirthName());
        assertEquals("Complement Adresse", physicalContactPoint7.getComplementAdresse());
        assertEquals("Libelle De La Voie", physicalContactPoint7.getLibelleDeLaVoie());
        assertEquals("Num", physicalContactPoint7.getNum());
        assertEquals("Type De Voie", physicalContactPoint7.getTypeDeVoie());
        assertEquals(1, physicalContactPoint7.getPhysicalContactPointTypeGId());
        assertTrue(physicalContactPoint7.isInFrance());
        assertSame(physicalContactPoint3, actualCreatePhysicalContactPointResult);
    }

    /**
     * Method under test:
     * {@link PhysicalContactPointServiceImpl#createPhysicalContactPoint(Long, PhysicalContactPoint)}
     */
    @Test
    void testCreatePhysicalContactPoint2() {
        // Arrange
        when(physicalContactPointRepository.save(Mockito.<PhysicalContactPoint>any()))
                .thenThrow(new RuntimeException("foo"));

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
        personParties3.setPhysicalContactPoint(new PhysicalContactPoint());
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

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> physicalContactPointServiceImpl.createPhysicalContactPoint(1L, physicalContactPoint4));
        verify(personPartiesRepository).findById(eq(1L));
        verify(physicalContactPointRepository).save(isA(PhysicalContactPoint.class));
    }

    /**
     * Method under test:
     * {@link PhysicalContactPointServiceImpl#getAdressById(Long)}
     */
    @Test
    void testGetAdressById() {
        // Arrange
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
        Optional<PhysicalContactPoint> ofResult = Optional.of(physicalContactPoint2);
        when(physicalContactPointRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        PhysicalContactPoint actualAdressById = physicalContactPointServiceImpl.getAdressById(1L);

        // Assert
        verify(physicalContactPointRepository).findById(eq(1L));
        assertSame(physicalContactPoint2, actualAdressById);
    }
}
