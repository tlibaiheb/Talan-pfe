package com.example.bpiappapi.pp.controllers;
import com.example.bpiappapi.pp.models.PersonParties;
import com.example.bpiappapi.pp.services.PersonPartiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonPartiesController {
    private final PersonPartiesService personPartiesService;
    @Autowired
    public PersonPartiesController(PersonPartiesService personPartiesService) {
        this.personPartiesService = personPartiesService;
    }
    @PostMapping("/api/v1/person-parties")
    public ResponseEntity<PersonParties> createOrganisationParty(@RequestBody PersonParties request) {
        try {
            PersonParties createdParty = personPartiesService.createPersonParties(request);


            return new ResponseEntity<>(createdParty, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/v1/person-parties/getbyid/{id}")
    public ResponseEntity<PersonParties> getPersonPartiesById(@PathVariable Long id) {
        try {
            PersonParties party = personPartiesService.getPersonPartiesById(id);
            return new ResponseEntity<>(party, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/api/v1/person-parties/{id}")
    public ResponseEntity<PersonParties> updatePersonParty(@PathVariable Long id, @RequestBody PersonParties request) {
        try {
            PersonParties updatedParty = personPartiesService.updatePersonParties(id, request);
            return new ResponseEntity<>(updatedParty, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/person-parties")
    public ResponseEntity<List<PersonParties>> getAllPersonParties() {
        try {
            List        <PersonParties> parties = personPartiesService.getAllPersonParties();
            return new ResponseEntity<>(parties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/api/v1/person-parties/by-cp-number/{cpNumber}")
    public ResponseEntity<List<PersonParties>> getPersonPartiesByCpNumber(@PathVariable String cpNumber) {
        try {
            List<PersonParties> personParties = personPartiesService.getPersonPartiesByCpNumber(cpNumber);
            return new ResponseEntity<>(personParties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/person-parties/by-birth-name/{birthName}")
    public ResponseEntity<List<PersonParties>> getPersonPartiesByBirthName(@PathVariable String birthName) {
        try {
            List<PersonParties> personParties = personPartiesService.getPersonPartiesByBirthName(birthName);
            return new ResponseEntity<>(personParties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/person-parties/by-main-first-name/{mainFirstName}")
    public ResponseEntity<List<PersonParties>> getPersonPartiesByMainFirstName(@PathVariable String mainFirstName) {
        try {
            List<PersonParties> personParties = personPartiesService.getPersonPartiesByMainFirstName(mainFirstName);
            return new ResponseEntity<>(personParties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/person-parties/by-idTiers/{idTiers}")
    public ResponseEntity<List<PersonParties>> getPersonPartiesByIdTiers(@PathVariable String idTiers) {
        try {
            List<PersonParties> personParties = personPartiesService.getPersonPartiesByIdTiers(idTiers);
            return new ResponseEntity<>(personParties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
