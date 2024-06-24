package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.FranceLocations;

import static org.junit.jupiter.api.Assertions.*;

 class FranceLocationsTest {

    @Test
     void testGetterAndSetters() {
        FranceLocations location = new FranceLocations();

        // Set all properties
        location.setId(1L);
        location.setLocationGId("test_location_gid");
        location.setLocationLabel("Test Location");
        location.setPostCode("75001");

        // Verify getters
        assertEquals(1L, location.getId());
        assertEquals("test_location_gid", location.getLocationGId());
        assertEquals("Test Location", location.getLocationLabel());
        assertEquals("75001", location.getPostCode());
    }

    @Test
     void testEquals() {
        FranceLocations location1 = new FranceLocations(1L, "test_location_gid", "Test Location", "75001");
        FranceLocations location2 = new FranceLocations(1L, "test_location_gid", "Test Location", "75001");

        // Check for equality with same object
        assertEquals(location1, location1);

        // Check for equality with different object but same properties
        assertEquals(location1, location2);

        // Check for inequality with different properties
        location2.setLocationLabel("Different Location");
        assertNotEquals(location1, location2);
    }

    @Test
     void testHashCode() {
        FranceLocations location1 = new FranceLocations(1L, "test_location_gid", "Test Location", "75001");
        FranceLocations location2 = new FranceLocations(1L, "test_location_gid", "Test Location", "75001");

        // Check for equal hash codes with same object
        assertEquals(location1.hashCode(), location1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(location1.hashCode(), location2.hashCode());

        // Check for different hash codes with different properties
        location2.setLocationLabel("Different Location");
        assertNotEquals(location1.hashCode(), location2.hashCode());
    }

    @Test
    void testBuilder() {
       // Using builder to create FranceLocations instance
       FranceLocations location = FranceLocations.builder()
               .id(1L)
               .locationGId("test_location_gid")
               .locationLabel("Test Location")
               .postCode("75001")
               .build();

       // Verify properties
       assertEquals(1L, location.getId());
       assertEquals("test_location_gid", location.getLocationGId());
       assertEquals("Test Location", location.getLocationLabel());
       assertEquals("75001", location.getPostCode());

       // Verify toString method
       String locationString = location.toString();
       assertTrue(locationString.contains("id=1"));
       assertTrue(locationString.contains("locationGId=test_location_gid"));
       assertTrue(locationString.contains("locationLabel=Test Location"));
       assertTrue(locationString.contains("postCode=75001"));
    }

    @Test
    void testBuilderMethods() {
       // Using builder methods directly
       FranceLocations.FranceLocationsBuilder builder = FranceLocations.builder();
       builder.id(1L);
       builder.locationGId("test_location_gid");
       builder.locationLabel("Test Location");
       builder.postCode("75001");

       FranceLocations location = builder.build();

       // Verify properties
       assertEquals(1L, location.getId());
       assertEquals("test_location_gid", location.getLocationGId());
       assertEquals("Test Location", location.getLocationLabel());
       assertEquals("75001", location.getPostCode());
    }
}

