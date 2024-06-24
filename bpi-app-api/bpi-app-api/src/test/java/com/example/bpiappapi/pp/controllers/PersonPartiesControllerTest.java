package com.example.bpiappapi.pp.controllers;

import com.example.bpiappapi.pp.models.PersonParties;
import com.example.bpiappapi.pp.services.PersonPartiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

 class PersonPartiesControllerTest {

    @Mock
    private PersonPartiesService personPartiesService;

    @InjectMocks
    private PersonPartiesController personPartiesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testCreateOrganisationParty() {
        PersonParties personParties = new PersonParties();
        when(personPartiesService.createPersonParties(any(PersonParties.class))).thenReturn(personParties);

        ResponseEntity<PersonParties> response = personPartiesController.createOrganisationParty(personParties);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(personParties, response.getBody());
    }

    @Test
     void testGetPersonPartiesById() {
        PersonParties personParties = new PersonParties();
        when(personPartiesService.getPersonPartiesById(anyLong())).thenReturn(personParties);

        ResponseEntity<PersonParties> response = personPartiesController.getPersonPartiesById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personParties, response.getBody());
    }

    @Test
     void testUpdatePersonParty() {
        PersonParties personParties = new PersonParties();
        when(personPartiesService.updatePersonParties(anyLong(), any(PersonParties.class))).thenReturn(personParties);

        ResponseEntity<PersonParties> response = personPartiesController.updatePersonParty(1L, personParties);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personParties, response.getBody());
    }

    @Test
     void testGetAllPersonParties() {
        List<PersonParties> personPartiesList = Collections.singletonList(new PersonParties());
        when(personPartiesService.getAllPersonParties()).thenReturn(personPartiesList);

        ResponseEntity<List<PersonParties>> response = personPartiesController.getAllPersonParties();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personPartiesList, response.getBody());
    }

    @Test
     void testGetPersonPartiesByCpNumber() {
        List<PersonParties> personPartiesList = Collections.singletonList(new PersonParties());
        when(personPartiesService.getPersonPartiesByCpNumber(anyString())).thenReturn(personPartiesList);

        ResponseEntity<List<PersonParties>> response = personPartiesController.getPersonPartiesByCpNumber("12345");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personPartiesList, response.getBody());
    }

    @Test
     void testGetPersonPartiesByBirthName() {
        List<PersonParties> personPartiesList = Collections.singletonList(new PersonParties());
        when(personPartiesService.getPersonPartiesByBirthName(anyString())).thenReturn(personPartiesList);

        ResponseEntity<List<PersonParties>> response = personPartiesController.getPersonPartiesByBirthName("John");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personPartiesList, response.getBody());
    }

    @Test
     void testGetPersonPartiesByMainFirstName() {
        List<PersonParties> personPartiesList = Collections.singletonList(new PersonParties());
        when(personPartiesService.getPersonPartiesByMainFirstName(anyString())).thenReturn(personPartiesList);

        ResponseEntity<List<PersonParties>> response = personPartiesController.getPersonPartiesByMainFirstName("John");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personPartiesList, response.getBody());
    }

    @Test
     void testGetPersonPartiesByIdTiers() {
        List<PersonParties> personPartiesList = Collections.singletonList(new PersonParties());
        when(personPartiesService.getPersonPartiesByIdTiers(anyString())).thenReturn(personPartiesList);

        ResponseEntity<List<PersonParties>> response = personPartiesController.getPersonPartiesByIdTiers("ID123");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personPartiesList, response.getBody());
    }
}
