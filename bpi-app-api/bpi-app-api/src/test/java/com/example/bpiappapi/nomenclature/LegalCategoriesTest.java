package com.example.bpiappapi.nomenclature;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.nomenclature.model.LegalCategories;

import static org.junit.jupiter.api.Assertions.*;

 class LegalCategoriesTest {

    @Test
     void testGetterAndSetters() {
        LegalCategories category = new LegalCategories();

        // Set all properties
        category.setId(1L);
        category.setGId("test_gid");
        category.setLabel("Test Label");

        // Verify getters
        assertEquals(1L, category.getId());
        assertEquals("test_gid", category.getGId());
        assertEquals("Test Label", category.getLabel());
    }

    @Test
     void testBuilder() {
        LegalCategories category = LegalCategories.builder()
                .id(1L)
                .gId("test_gid")
                .label("Test Label")
                .build();

        // Assertions (same as getter/setter test)
        // ...
    }

    @Test
     void testEquals() {
        LegalCategories category1 = new LegalCategories(1L, "test_gid", "Test Label");
        LegalCategories category2 = new LegalCategories(1L, "test_gid", "Test Label");

        // Check for equality with same object
        assertEquals(category1, category1);

        // Check for equality with different object but same properties
        assertEquals(category1, category2);

        // Check for inequality with different properties
        category2.setLabel("Different Label");
        assertNotEquals(category1, category2);
    }

    @Test
     void testHashCode() {
        LegalCategories category1 = new LegalCategories(1L, "test_gid", "Test Label");
        LegalCategories category2 = new LegalCategories(1L, "test_gid", "Test Label");

        // Check for equal hash codes with same object
        assertEquals(category1.hashCode(), category1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(category1.hashCode(), category2.hashCode());

        // Check for different hash codes with different properties
        category2.setLabel("Different Label");
        assertNotEquals(category1.hashCode(), category2.hashCode());
    }
}