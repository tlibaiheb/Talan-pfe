package com.example.bpiappapi.pp.models;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.pp.models.PhysicalContactPoint;

import static org.junit.jupiter.api.Assertions.*;

 class PhysicalContactPointTest {

    @Test
     void testGetterAndSetters() {
        PhysicalContactPoint contactPoint = new PhysicalContactPoint();

        // Set all properties
        contactPoint.setId(1L);
        contactPoint.setInFrance(true);
        contactPoint.setAddress1("Test Address 1");
        contactPoint.setAddress2("Test Address 2");
        contactPoint.setAddress3("Test Address 3");
        contactPoint.setAddress4("Test Address 4");
        contactPoint.setFrLocationGId("frLocationGId");
        contactPoint.setCountryGId("countryGId");
        contactPoint.setOutOfFrTown("Out of France Town");
        contactPoint.setOutOfFrPostCode("Out of France Postcode");
        contactPoint.setPhysicalContactPointTypeGId(123);
        contactPoint.setNum("12345");
        contactPoint.setTypeDeVoie("Street Type");
        contactPoint.setLibelleDeLaVoie("Street Name");
        contactPoint.setRegionOutFr("Out of France Region");
        contactPoint.setComplementAdresse("Address Complement");

        // Verify getters
        assertEquals(1L, contactPoint.getId());
        assertTrue(contactPoint.isInFrance());
        assertEquals("Test Address 1", contactPoint.getAddress1());
        assertEquals("Test Address 2", contactPoint.getAddress2());
        assertEquals("Test Address 3", contactPoint.getAddress3());
        assertEquals("Test Address 4", contactPoint.getAddress4());
        assertEquals("frLocationGId", contactPoint.getFrLocationGId());
        assertEquals("countryGId", contactPoint.getCountryGId());
        assertEquals("Out of France Town", contactPoint.getOutOfFrTown());
        assertEquals("Out of France Postcode", contactPoint.getOutOfFrPostCode());
        assertEquals(123, contactPoint.getPhysicalContactPointTypeGId());
        assertEquals("12345", contactPoint.getNum());
        assertEquals("Street Type", contactPoint.getTypeDeVoie());
        assertEquals("Street Name", contactPoint.getLibelleDeLaVoie());
        assertEquals("Out of France Region", contactPoint.getRegionOutFr());
        assertEquals("Address Complement", contactPoint.getComplementAdresse());
    }

    @Test
     void testEquals() {
        PhysicalContactPoint contactPoint1 = createPhysicalContactPoint();
        PhysicalContactPoint contactPoint2 = createPhysicalContactPoint();

        // Check for equality with same object
        assertEquals(contactPoint1, contactPoint1);

        // Check for equality with different object but same properties
        assertEquals(contactPoint1, contactPoint2);

        // Check for inequality with different properties (e.g., inFrance)
        contactPoint2.setInFrance(false);
        assertNotEquals(contactPoint1, contactPoint2);
    }

    @Test
     void testHashCode() {
        PhysicalContactPoint contactPoint1 = createPhysicalContactPoint();
        PhysicalContactPoint contactPoint2 = createPhysicalContactPoint();

        // Check for equal hash codes with same object
        assertEquals(contactPoint1.hashCode(), contactPoint1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(contactPoint1.hashCode(), contactPoint2.hashCode());

        // Check for different hash codes with different properties (e.g., inFrance)
        contactPoint2.setInFrance(false);
        assertNotEquals(contactPoint1.hashCode(), contactPoint2.hashCode());
    }

    private PhysicalContactPoint createPhysicalContactPoint() {
        PhysicalContactPoint contactPoint = new PhysicalContactPoint();
        contactPoint.setId(1L);
        contactPoint.setInFrance(true);
        // ... set other properties
        return contactPoint;
    }


}