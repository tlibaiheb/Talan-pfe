package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.PhysicalContactPointType;

import static org.junit.jupiter.api.Assertions.*;

 class PhysicalContactPointTypeTest {

    @Test
     void testGetterAndSetters() {
        PhysicalContactPointType type = new PhysicalContactPointType();

        // Set all properties
        type.setId(1L);
        type.setGId(123);
        type.setLabel("Test Type");

        // Verify getters
        assertEquals(1L, type.getId());
        assertEquals(123, type.getGId());
        assertEquals("Test Type", type.getLabel());
    }

    @Test
     void testBuilder() {
        PhysicalContactPointType type = PhysicalContactPointType.builder()
                .id(1L)
                .gId(123)
                .label("Test Type")
                .build();

        // Assertions (same as getter/setter test)
        // ...
    }

    @Test
     void testEquals() {
        PhysicalContactPointType type1 = new PhysicalContactPointType(1L, 123, "Test Type");
        PhysicalContactPointType type2 = new PhysicalContactPointType(1L, 123, "Test Type");

        // Check for equality with same object
        assertEquals(type1, type1);

        // Check for equality with different object but same properties
        assertEquals(type1, type2);

        // Check for inequality with different properties
        type2.setLabel("Different Type");
        assertNotEquals(type1, type2);
    }

    @Test
     void testHashCode() {
        PhysicalContactPointType type1 = new PhysicalContactPointType(1L, 123, "Test Type");
        PhysicalContactPointType type2 = new PhysicalContactPointType(1L, 123, "Test Type");

        // Check for equal hash codes with same object
        assertEquals(type1.hashCode(), type1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(type1.hashCode(), type2.hashCode());

        // Check for different hash codes with different properties
        type2.setLabel("Different Type");
        assertNotEquals(type1.hashCode(), type2.hashCode());
    }
}