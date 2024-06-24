package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.LegalForms;

import static org.junit.jupiter.api.Assertions.*;

  class LegalFormsTest {

    @Test
      void testGetterAndSetters() {
        LegalForms form = new LegalForms();

        // Set all properties
        form.setId(1L);
        form.setCode(123);
        form.setGId("test_gid");
        form.setLabel("Test Label");

        // Verify getters
        assertEquals(1L, form.getId());
        assertEquals(123, form.getCode());
        assertEquals("test_gid", form.getGId());
        assertEquals("Test Label", form.getLabel());
    }

    @Test
      void testEquals() {
        LegalForms form1 = new LegalForms(1L, 123, "test_gid", "Test Label");
        LegalForms form2 = new LegalForms(1L, 123, "test_gid", "Test Label");

        // Check for equality with same object
        assertEquals(form1, form1);

        // Check for equality with different object but same properties
        assertEquals(form1, form2);

        // Check for inequality with different properties
        form2.setLabel("Different Label");
        assertNotEquals(form1, form2);
    }

    @Test
      void testHashCode() {
        LegalForms form1 = new LegalForms(1L, 123, "test_gid", "Test Label");
        LegalForms form2 = new LegalForms(1L, 123, "test_gid", "Test Label");

        // Check for equal hash codes with same object
        assertEquals(form1.hashCode(), form1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(form1.hashCode(), form2.hashCode());

        // Check for different hash codes with different properties
        form2.setLabel("Different Label");
        assertNotEquals(form1.hashCode(), form2.hashCode());
    }

    @Test
    void testBuilder() {
      // Using builder to create LegalForms instance
      LegalForms form = LegalForms.builder()
              .id(1L)
              .code(123)
              .gId("test_gid")
              .label("Test Label")
              .build();

      // Verify properties
      assertEquals(1L, form.getId());
      assertEquals(123, form.getCode());
      assertEquals("test_gid", form.getGId());
      assertEquals("Test Label", form.getLabel());

      // Verify toString method
      String formString = form.toString();
      assertTrue(formString.contains("id=1"));
      assertTrue(formString.contains("code=123"));
      assertTrue(formString.contains("gId=test_gid"));
      assertTrue(formString.contains("label=Test Label"));
    }

    @Test
    void testBuilderMethods() {
      // Using builder methods directly
      LegalForms.LegalFormsBuilder builder = LegalForms.builder();
      builder.id(1L);
      builder.code(123);
      builder.gId("test_gid");
      builder.label("Test Label");

      LegalForms form = builder.build();

      // Verify properties
      assertEquals(1L, form.getId());
      assertEquals(123, form.getCode());
      assertEquals("test_gid", form.getGId());
      assertEquals("Test Label", form.getLabel());
    }
}