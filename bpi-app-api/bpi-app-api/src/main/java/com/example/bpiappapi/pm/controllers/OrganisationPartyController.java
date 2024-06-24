package com.example.bpiappapi.pm.controllers;

import com.example.bpiappapi.pm.models.OrganisationPartyIdentification;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import com.example.bpiappapi.pm.services.OrganisationPartyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganisationPartyController {

    private final OrganisationPartyService organisationPartyService;

    @Autowired
    public OrganisationPartyController(OrganisationPartyService organisationPartyService) {
        this.organisationPartyService = organisationPartyService;
    }

    @PostMapping("/api/v1/organisation-parties")
    public ResponseEntity<OrganisationPartyRequest> createOrganisationParty(@RequestBody OrganisationPartyRequest request) {
        try {
            OrganisationPartyRequest createdParty = organisationPartyService.createOrganisationParty(request);


            return new ResponseEntity<>(createdParty, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/organisation-parties/filtrer")
    public ResponseEntity<List<OrganisationPartyRequest>> filtrerListe(@RequestParam String selectedOption, @RequestParam String searchValue) {
        try {
            List<OrganisationPartyRequest> filteredParties = organisationPartyService.filtrerListe(selectedOption, searchValue);
            return new ResponseEntity<>(filteredParties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



   /* @DeleteMapping("/api/v1/organisation-parties/{id}")
    public ResponseEntity<String> deleteOrganisationParty(@PathVariable Long id) {
        try {
            organisationPartyService.deleteOrganisationParty(id);
            return new ResponseEntity<>("Organisation Party deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete Organisation Party", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    @GetMapping("/api/v1/organisation-parties/getbyid/{id}")
    public ResponseEntity<OrganisationPartyRequest> getOrganisationPartyById(@PathVariable Long id) {
        try {
            OrganisationPartyRequest party = organisationPartyService.getOrganisationPartyById(id);
            return new ResponseEntity<>(party, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/api/v1/organisation-parties/update/{id}")
    public ResponseEntity<OrganisationPartyRequest> updateOrganisationParty(@PathVariable Long id, @RequestBody OrganisationPartyRequest request) {
        try {
            OrganisationPartyRequest updatedParty = organisationPartyService.updateOrganisationParty(id, request);
            return ResponseEntity.ok(updatedParty);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/v1/organisation-parties")
    public ResponseEntity<List<OrganisationPartyRequest>> getAllOrganisationParties() {
        try {
            List        <OrganisationPartyRequest> parties = organisationPartyService.getAllOrganisationParties();
            return new ResponseEntity<>(parties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/api/v1/organisation-parties/existsBySiren/{siren}")
    public ResponseEntity<Boolean> getOrganisationPartyexisteBySiren(@PathVariable String siren) {
        try {
            boolean exists = organisationPartyService.existsBySiren(siren);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/api/v1/organisation-parties/getbysiren/{siren}")
    public ResponseEntity<OrganisationPartyRequest> getOrganisationPartyBySiren(@PathVariable String siren) {
        try {
            OrganisationPartyRequest party = organisationPartyService.getBySiren(siren);
            return ResponseEntity.ok(party);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }









}



