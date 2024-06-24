package com.example.bpiappapi.pm.controllers;

import com.example.bpiappapi.pm.models.AddressForm;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import com.example.bpiappapi.pm.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/api/v1//organisation-parties/{organisationPId}/physical-contact-points")
    public ResponseEntity<AddressForm> createAddress(@PathVariable("organisationPId") Long organisationPId,@RequestBody AddressForm addressForm) {
        AddressForm createdAddress = addressService.createAddress(organisationPId,addressForm);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1//organisation-parties/physical-contact-points/{id}")
    public ResponseEntity<AddressForm> getAddressyById(@PathVariable Long id) {
        try {
            AddressForm adress = addressService.getAdressById(id);
            return new ResponseEntity<>(adress, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

