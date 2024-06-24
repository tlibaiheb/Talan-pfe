package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.GicsCodes;

import static org.junit.jupiter.api.Assertions.*;

 class GicsCodesTest {

    @Test
     void testGetterAndSetters() {
        GicsCodes code = new GicsCodes();

        // Set all properties
        code.setId(1L);
        code.setGId("test_gid");
        code.setLabel("Test Label");

        // Verify getters
        assertEquals(1L, code.getId());
        assertEquals("test_gid", code.getGId());
        assertEquals("Test Label", code.getLabel());
    }

    @Test
     void testBuilder() {
        GicsCodes code = GicsCodes.builder()
                .id(1L)
                .gId("test_gid")
                .label("Test Label")
                .build();

        // Assertions (same as getter/setter test)
        // ...
    }

    @Test
     void testEquals() {
        GicsCodes code1 = new GicsCodes(1L, "test_gid", "Test Label");
        GicsCodes code2 = new GicsCodes(1L, "test_gid", "Test Label");

        // Check for equality with same object
        assertEquals(code1, code1);

        // Check for equality with different object but same properties
        assertEquals(code1, code2);

        // Check for inequality with different properties
        code2.setLabel("Different Label");
        assertNotEquals(code1, code2);
    }

    @Test
     void testHashCode() {
        GicsCodes code1 = new GicsCodes(1L, "test_gid", "Test Label");
        GicsCodes code2 = new GicsCodes(1L, "test_gid", "Test Label");

        // Check for equal hash codes with same object
        assertEquals(code1.hashCode(), code1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(code1.hashCode(), code2.hashCode());

        // Check for different hash codes with different properties
        code2.setLabel("Different Label");
        assertNotEquals(code1.hashCode(), code2.hashCode());
    }
}