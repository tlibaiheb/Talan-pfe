package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.PersonRoleTypes;

import static org.junit.jupiter.api.Assertions.*;

 class PersonRoleTypesTest {

    @Test
     void testGetterAndSetters() {
        PersonRoleTypes roleType = new PersonRoleTypes();

        // Set all properties
        roleType.setId(1L);
        roleType.setGId(123);
        roleType.setLabel("Test Role Type");

        // Verify getters
        assertEquals(1L, roleType.getId());
        assertEquals(123, roleType.getGId());
        assertEquals("Test Role Type", roleType.getLabel());
    }

    @Test
     void testEquals() {
        PersonRoleTypes roleType1 = new PersonRoleTypes(1L, 123, "Test Role Type");
        PersonRoleTypes roleType2 = new PersonRoleTypes(1L, 123, "Test Role Type");

        // Check for equality with same object
        assertEquals(roleType1, roleType1);

        // Check for equality with different object but same properties
        assertEquals(roleType1, roleType2);

        // Check for inequality with different properties
        roleType2.setLabel("Different Role Type");
        assertNotEquals(roleType1, roleType2);
    }

    @Test
     void testHashCode() {
        PersonRoleTypes roleType1 = new PersonRoleTypes(1L, 123, "Test Role Type");
        PersonRoleTypes roleType2 = new PersonRoleTypes(1L, 123, "Test Role Type");

        // Check for equal hash codes with same object
        assertEquals(roleType1.hashCode(), roleType1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(roleType1.hashCode(), roleType2.hashCode());

        // Check for different hash codes with different properties
        roleType2.setLabel("Different Role Type");
        assertNotEquals(roleType1.hashCode(), roleType2.hashCode());
    }

    @Test
    void testBuilder() {
       // Using builder to create PersonRoleTypes instance
       PersonRoleTypes roleType = PersonRoleTypes.builder()
               .id(1L)
               .gId(123)
               .label("Test Role Type")
               .build();

       // Verify properties
       assertEquals(1L, roleType.getId());
       assertEquals(123, roleType.getGId());
       assertEquals("Test Role Type", roleType.getLabel());

       // Verify toString method
       String roleTypeString = roleType.toString();
       assertTrue(roleTypeString.contains("id=1"));
       assertTrue(roleTypeString.contains("gId=123"));
       assertTrue(roleTypeString.contains("label=Test Role Type"));
    }

    @Test
    void testBuilderMethods() {
       // Using builder methods directly
       PersonRoleTypes.PersonRoleTypesBuilder builder = PersonRoleTypes.builder();
       builder.id(1L);
       builder.gId(123);
       builder.label("Test Role Type");

       PersonRoleTypes roleType = builder.build();

       // Verify properties
       assertEquals(1L, roleType.getId());
       assertEquals(123, roleType.getGId());
       assertEquals("Test Role Type", roleType.getLabel());
    }
}