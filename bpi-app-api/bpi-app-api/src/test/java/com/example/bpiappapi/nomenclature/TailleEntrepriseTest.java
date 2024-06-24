package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.TailleEntreprise;

import static org.junit.jupiter.api.Assertions.*;

 class TailleEntrepriseTest {

    @Test
     void testGetterAndSetters() {
        TailleEntreprise size = new TailleEntreprise();

        // Set all properties
        size.setId(1L);
        size.setGId(123);
        size.setLabel("Test Size Label");

        // Verify getters
        assertEquals(1L, size.getId());
        assertEquals(123, size.getGId());
        assertEquals("Test Size Label", size.getLabel());
    }

     @Test
      void testBuilder() {
         TailleEntreprise size = TailleEntreprise.builder()
                 .id(1L)
                 .gId(123)
                 .label("Test Size Label")
                 .build();

         // Assertions using getters (similar to getter/setter test)
         assertEquals(1L, size.getId());
         assertEquals(123, size.getGId());
         assertEquals("Test Size Label", size.getLabel());
     }

    @Test
     void testEquals() {
        TailleEntreprise size1 = new TailleEntreprise(1L, 123, "Test Size Label");
        TailleEntreprise size2 = new TailleEntreprise(1L, 123, "Test Size Label");

        // Check for equality with same object
        assertEquals(size1, size1);

        // Check for equality with different object but same properties
        assertEquals(size1, size2);

        // Check for inequality with different properties
        size2.setLabel("Different Size Label");
        assertNotEquals(size1, size2);
    }

    @Test
     void testHashCode() {
        TailleEntreprise size1 = new TailleEntreprise(1L, 123, "Test Size Label");
        TailleEntreprise size2 = new TailleEntreprise(1L, 123, "Test Size Label");

        // Check for equal hash codes with same object
        assertEquals(size1.hashCode(), size1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(size1.hashCode(), size2.hashCode());

        // Check for different hash codes with different properties
        size2.setLabel("Different Size Label");
        assertNotEquals(size1.hashCode(), size2.hashCode());
    }
}