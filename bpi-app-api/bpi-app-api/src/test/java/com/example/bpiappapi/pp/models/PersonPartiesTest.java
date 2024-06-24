package com.example.bpiappapi.pp.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class PersonPartiesTest {

    @Test
    void testConstructorAndGetters() throws ParseException {
        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Long id = 1L;
        int namePrefixGId = 1;
        String birthName = "John";
        String mainFirstName = "Doe";
        String firstNames = "John Doe";
        Date birthDate = sdf.parse("2000-01-01");
        String idTiers = "123456";
        String countryOfBirthGId = "US";
        String useName = "JohnDoe";
        String cpNumber = "12345";
        String outOfFrBirthPostCode = "54321";
        String outOfFrPlaceOfBirth = "New York";
        String frPlaceOfBirthGId = "NY";

        // Act
        PersonParties personParties = new PersonParties(id, namePrefixGId, birthName, mainFirstName, firstNames, birthDate, idTiers, countryOfBirthGId, useName, cpNumber, outOfFrBirthPostCode, outOfFrPlaceOfBirth, frPlaceOfBirthGId, null);

        // Assert
        assertEquals(id, personParties.getId());
        assertEquals(namePrefixGId, personParties.getNamePrefixGId());
        assertEquals(birthName, personParties.getBirthName());
        assertEquals(mainFirstName, personParties.getMainFirstName());
        assertEquals(firstNames, personParties.getFirstNames());
        assertEquals(birthDate, personParties.getBirthDate());
        assertEquals(idTiers, personParties.getIdTiers());
        assertEquals(countryOfBirthGId, personParties.getCountryOfBirthGId());
        assertEquals(useName, personParties.getUseName());
        assertEquals(cpNumber, personParties.getCpNumber());
        assertEquals(outOfFrBirthPostCode, personParties.getOutOfFrBirthPostCode());
        assertEquals(outOfFrPlaceOfBirth, personParties.getOutOfFrPlaceOfBirth());
        assertEquals(frPlaceOfBirthGId, personParties.getFrPlaceOfBirthGId());
    }

    @Test
    void testSetter() throws ParseException {
        // Arrange
        PersonParties personParties = new PersonParties();
        String idTiers = "654321";

        // Act
        personParties.setIdTiers(idTiers);

        // Assert
        assertEquals(idTiers, personParties.getIdTiers());
    }

    @Test
    void testEqualsAndHashCode() throws ParseException {
        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = sdf.parse("2000-01-01");
        PersonParties person1 = new PersonParties(1L, 1, "John", "Doe", "John Doe", birthDate, "123456", "US", "JohnDoe", "12345", "54321", "New York", "NY", null);
        PersonParties person2 = new PersonParties(1L, 1, "John", "Doe", "John Doe", birthDate, "123456", "US", "JohnDoe", "12345", "54321", "New York", "NY", null);
        PersonParties person3 = new PersonParties(2L, 2, "Jane", "Doe", "Jane Doe", birthDate, "654321", "FR", "JaneDoe", "54321", "12345", "Paris", "FR", null);

        // Act & Assert
        assertEquals(person1, person2);
        assertNotEquals(person1, person3);
        assertEquals(person1.hashCode(), person2.hashCode());
        assertNotEquals(person1.hashCode(), person3.hashCode());
    }


}