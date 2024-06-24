package com.example.bpiappapi.notification;

import org.junit.jupiter.api.Test;
import com.example.bpiappapi.notification.Notification;

import static org.junit.jupiter.api.Assertions.*;

 class NotificationTest {

    @Test
     void testGetterAndSetters() {
        Notification notification = new Notification();

        // Set all properties
        notification.setId(1L);
        notification.setIdUser(123L);
        notification.setSiren("123456789");
        notification.setIdPm(456L);
        notification.setExecuted(true);

        // Verify getters
        assertEquals(1L, notification.getId());
        assertEquals(123L, notification.getIdUser());
        assertEquals("123456789", notification.getSiren());
        assertEquals(456L, notification.getIdPm());
        assertTrue(notification.isExecuted());
    }

    @Test
     void testEquals() {
        Notification notification1 = new Notification(1L, 123L, "123456789", 456L, true);
        Notification notification2 = new Notification(1L, 123L, "123456789", 456L, true);

        // Check for equality with same object
        assertEquals(notification1, notification1);

        // Check for equality with different object but same properties
        assertEquals(notification1, notification2);

        // Check for inequality with different properties
        notification2.setExecuted(false);
        assertNotEquals(notification1, notification2);
    }

    @Test
     void testHashCode() {
        Notification notification1 = new Notification(1L, 123L, "123456789", 456L, true);
        Notification notification2 = new Notification(1L, 123L, "123456789", 456L, true);

        // Check for equal hash codes with same object
        assertEquals(notification1.hashCode(), notification1.hashCode());

        // Check for equal hash codes with different object but same properties
        assertEquals(notification1.hashCode(), notification2.hashCode());

        // Check for different hash codes with different properties
        notification2.setExecuted(false);
        assertNotEquals(notification1.hashCode(), notification2.hashCode());
    }
}