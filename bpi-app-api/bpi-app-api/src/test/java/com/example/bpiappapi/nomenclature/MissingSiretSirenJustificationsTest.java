package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.MissingSiretSirenJustifications;

import static org.junit.jupiter.api.Assertions.*;

 class MissingSiretSirenJustificationsTest {

    @Test
     void testGetterAndSetters() {
        MissingSiretSirenJustifications justification = new MissingSiretSirenJustifications();

        // Set all properties
        justification.setId(1L);
        justification.setGId(123);
        justification.setLabel("Test Justification");

        // Verify getters
        assertEquals(1L, justification.getId());
        assertEquals(123, justification.getGId());
        assertEquals("Test Justification", justification.getLabel());
    }

    @Test
     void testBuilder() {
        MissingSiretSirenJustifications justification = MissingSiretSirenJustifications.builder()
                .id(1L)
                .gId(123)
                .label("Test Justification")
                .build();

        // Assertions (same as getter/setter test)
        // ...
    }

    @Test
     void testEquals() {
        MissingSiretSirenJustifications justification1 = new MissingSiretSirenJustifications(1L, 123, "Test Justification");
        MissingSiretSirenJustifications justification2 = new MissingSiretSirenJustifications(1L, 123, "Test Justification");

        // Check for equality with same object
        assertEquals(justification1, justification1);

        // Check for equality with different object but same properties
        assertEquals(justification1, justification2);

        // Check for inequality with different properties
        justification2.setLabel("Different Justification");
        assertNotEquals(justification1, justification2);
    }

    @Test
     void testHashCode() {
        MissingSiretSirenJustifications justification1 = new MissingSiretSirenJustifications(1L, 123, "Test Justification");
        MissingSiretSirenJustifications justification2 = new MissingSiretSirenJustifications(1L, 123, "Test Justification");

        // Check for equal hash codes with same object
        assertEquals(justification1.hashCode(), justification1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(justification1.hashCode(), justification2.hashCode());

        // Check for different hash codes with different properties
        justification2.setLabel("Different Justification");
        assertNotEquals(justification1.hashCode(), justification2.hashCode());
    }
}