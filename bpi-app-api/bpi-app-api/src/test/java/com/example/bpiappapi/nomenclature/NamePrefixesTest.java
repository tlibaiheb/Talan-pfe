package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.NamePrefixes;

import static org.junit.jupiter.api.Assertions.*;

 class NamePrefixesTest {

    @Test
     void testGetterAndSetters() {
        NamePrefixes prefix = new NamePrefixes();

        // Set all properties
        prefix.setId(1L);
        prefix.setGId(123);
        prefix.setLabel("Test Prefix");

        // Verify getters
        assertEquals(1L, prefix.getId());
        assertEquals(123, prefix.getGId());
        assertEquals("Test Prefix", prefix.getLabel());
    }

    @Test
     void testBuilder() {
        NamePrefixes prefix = NamePrefixes.builder()
                .id(1L)
                .gId(123)
                .label("Test Prefix")
                .build();

        // Assertions (same as getter/setter test)
        // ...
    }

    @Test
     void testEquals() {
        NamePrefixes prefix1 = new NamePrefixes(1L, 123, "Test Prefix");
        NamePrefixes prefix2 = new NamePrefixes(1L, 123, "Test Prefix");

        // Check for equality with same object
        assertEquals(prefix1, prefix1);

        // Check for equality with different object but same properties
        assertEquals(prefix1, prefix2);

        // Check for inequality with different properties
        prefix2.setLabel("Different Prefix");
        assertNotEquals(prefix1, prefix2);
    }

    @Test
     void testHashCode() {
        NamePrefixes prefix1 = new NamePrefixes(1L, 123, "Test Prefix");
        NamePrefixes prefix2 = new NamePrefixes(1L, 123, "Test Prefix");

        // Check for equal hash codes with same object
        assertEquals(prefix1.hashCode(), prefix1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(prefix1.hashCode(), prefix2.hashCode());

        // Check for different hash codes with different properties
        prefix2.setLabel("Different Prefix");
        assertNotEquals(prefix1.hashCode(), prefix2.hashCode());
    }
}