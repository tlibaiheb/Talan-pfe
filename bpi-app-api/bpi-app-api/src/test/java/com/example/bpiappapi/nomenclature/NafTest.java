package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.Naf;

import static org.junit.jupiter.api.Assertions.*;

 class NafTest {

    @Test
    void testGetterAndSetters() {
        Naf naf = new Naf();

        // Set all properties
        naf.setId(1L);
        naf.setCode("123456"); // Assuming code is a String
        naf.setGId("test_gid");
        naf.setLabel("Test NAF Label");

        // Verify getters
        assertEquals(1L, naf.getId());
        assertEquals("123456", naf.getCode());
        assertEquals("test_gid", naf.getGId());
        assertEquals("Test NAF Label", naf.getLabel());
    }

    @Test
     void testEquals() {
        Naf naf1 = new Naf(1L, "123456", "test_gid", "Test NAF Label");
        Naf naf2 = new Naf(1L, "123456", "test_gid", "Test NAF Label");

        // Check for equality with same object
        assertEquals(naf1, naf1);

        // Check for equality with different object but same properties
        assertEquals(naf1, naf2);

        // Check for inequality with different properties
        naf2.setLabel("Different NAF Label");
        assertNotEquals(naf1, naf2);
    }

    @Test
    void testHashCode() {
        Naf naf1 = new Naf(1L, "123456", "test_gid", "Test NAF Label");
        Naf naf2 = new Naf(1L, "123456", "test_gid", "Test NAF Label");

        // Check for equal hash codes with same object
        assertEquals(naf1.hashCode(), naf1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(naf1.hashCode(), naf2.hashCode());

        // Check for different hash codes with different properties
        naf2.setLabel("Different NAF Label");
        assertNotEquals(naf1.hashCode(), naf2.hashCode());
    }

     @Test
     void testBuilder() {
         // Using builder to create Naf instance
         Naf naf = Naf.builder()
                 .id(1L)
                 .code("123456")
                 .gId("test_gid")
                 .label("Test NAF Label")
                 .build();

         // Verify properties
         assertEquals(1L, naf.getId());
         assertEquals("123456", naf.getCode());
         assertEquals("test_gid", naf.getGId());
         assertEquals("Test NAF Label", naf.getLabel());

         // Verify toString method
         String nafString = naf.toString();
         assertTrue(nafString.contains("id=1"));
         assertTrue(nafString.contains("code=123456"));
         assertTrue(nafString.contains("gId=test_gid"));
         assertTrue(nafString.contains("label=Test NAF Label"));
     }

     @Test
     void testBuilderMethods() {
         // Using builder methods directly
         Naf.NafBuilder builder = Naf.builder();
         builder.id(1L);
         builder.code("123456");
         builder.gId("test_gid");
         builder.label("Test NAF Label");

         Naf naf = builder.build();

         // Verify properties
         assertEquals(1L, naf.getId());
         assertEquals("123456", naf.getCode());
         assertEquals("test_gid", naf.getGId());
         assertEquals("Test NAF Label", naf.getLabel());
     }
}