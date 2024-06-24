package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.Country;

import static org.junit.jupiter.api.Assertions.*;

 class CountryTest {

    @Test
     void testGetterAndSetters() {
        Country country = new Country();

        // Set all properties
        country.setId(1L);
        country.setCountryGId("test_country_gid");
        country.setCountryLabel("Test Country");
        country.setInternalBpiCode("TSTC");
        country.setIso2Code("TC");
        country.setIso3Code("TST");
        country.setNationalityLabel("Test Citizens");
        country.setNumericalCode("123");
        country.setPhoneCountryCode("+123");

        // Verify getters
        assertEquals(1L, country.getId());
        assertEquals("test_country_gid", country.getCountryGId());
        assertEquals("Test Country", country.getCountryLabel());
        assertEquals("TSTC", country.getInternalBpiCode());
        assertEquals("TC", country.getIso2Code());
        assertEquals("TST", country.getIso3Code());
        assertEquals("Test Citizens", country.getNationalityLabel());
        assertEquals("123", country.getNumericalCode());
        assertEquals("+123", country.getPhoneCountryCode());
    }

    @Test
     void testBuilder() {
        // Use Lombok's builder (if applicable)
        Country country = Country.builder()
                .id(1L)
                .countryGId("test_country_gid")
                .countryLabel("Test Country")
                .internalBpiCode("TSTC")
                .iso2Code("TC")
                .iso3Code("TST")
                .nationalityLabel("Test Citizens")
                .numericalCode("123")
                .phoneCountryCode("+123")
                .build();

        // Assertions (same as getter/setter test)
        // ...
    }

    @Test
     void testEquals() {
        Country country1 = new Country(1L, "test_country_gid", "Test Country", "TSTC", "TC", "TST", "Test Citizens", "123", "+123");
        Country country2 = new Country(1L, "test_country_gid", "Test Country", "TSTC", "TC", "TST", "Test Citizens", "123", "+123");

        // Check for equality with same object
        assertEquals(country1, country1);

        // Check for equality with different object but same properties
        assertEquals(country1, country2);

        // Check for inequality with different properties
        country2.setCountryLabel("Different Country");
        assertNotEquals(country1, country2);
    }

    @Test
     void testHashCode() {
        Country country1 = new Country(1L, "test_country_gid", "Test Country", "TSTC", "TC", "TST", "Test Citizens", "123", "+123");
        Country country2 = new Country(1L, "test_country_gid", "Test Country", "TSTC", "TC", "TST", "Test Citizens", "123", "+123");

        // Check for equal hash codes with same object
        assertEquals(country1.hashCode(), country1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(country1.hashCode(), country2.hashCode());

        // Check for different hash codes with different properties
        country2.setCountryLabel("Different Country");
        assertNotEquals(country1.hashCode(), country2.hashCode());
    }
}