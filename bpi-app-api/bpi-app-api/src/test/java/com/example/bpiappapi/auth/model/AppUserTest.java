package com.example.bpiappapi.auth.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AppUserTest {

    @Test
    void testGetAuthorities() {
        AppUserRole userRole = AppUserRole.USER;
        AppUser appUser = new AppUser("John", "Doe", "john@example.com", "password", userRole);

        assertEquals(1, appUser.getAuthorities().size());
        assertTrue(appUser.getAuthorities().contains(new SimpleGrantedAuthority(userRole.name())));
    }

    @Test
    void testGettersAndSetters() {
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setFirstName("John");
        appUser.setLastName("Doe");
        appUser.setEmail("john@example.com");
        appUser.setPassword("password");
        appUser.setAppUserRole(AppUserRole.USER);
        appUser.setLocked(false);
        appUser.setEnabled(true);

        assertEquals(1L, appUser.getId());
        assertEquals("John", appUser.getFirstName());
        assertEquals("Doe", appUser.getLastName());
        assertEquals("john@example.com", appUser.getEmail());
        assertEquals("password", appUser.getPassword());
        assertEquals(AppUserRole.USER, appUser.getAppUserRole());
        assertFalse(appUser.getLocked());
        assertTrue(appUser.getEnabled());
    }

    @Test
    void testIsAccountNonExpired() {
        AppUser appUser = new AppUser();
        assertTrue(appUser.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        AppUser appUser = new AppUser();
        appUser.setLocked(false);
        assertTrue(appUser.isAccountNonLocked());
        appUser.setLocked(true);
        assertFalse(appUser.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        AppUser appUser = new AppUser();
        assertTrue(appUser.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        AppUser appUser = new AppUser();
        appUser.setEnabled(true);
        assertTrue(appUser.isEnabled());
        appUser.setEnabled(false);
        assertFalse(appUser.isEnabled());
    }
}
