package com.example.bpiappapi.pp.controllers;
import com.example.bpiappapi.pp.models.PhysicalContactPoint;
import com.example.bpiappapi.pp.services.PhysicalContactPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1//person-parties/{personPId}/physical-contact-points")
public class PhysicalContactPointServiceController {
    private final PhysicalContactPointService physicalContactPointService;
    @Autowired
    public PhysicalContactPointServiceController(PhysicalContactPointService physicalContactPointService) {
        this.physicalContactPointService = physicalContactPointService;
    }
    @PostMapping
    public ResponseEntity<PhysicalContactPoint> createAddress(@PathVariable("personPId") Long personPId, @RequestBody PhysicalContactPoint physicalContactPoint) {
        PhysicalContactPoint createdAddress = physicalContactPointService.createPhysicalContactPoint(personPId,physicalContactPoint);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }
    @GetMapping("/api/v1//person-parties/physical-contact-points/{id}")
    public ResponseEntity<PhysicalContactPoint> getAddressyById(@PathVariable Long id) {
        try {
            PhysicalContactPoint adress = physicalContactPointService.getAdressById(id);
            return new ResponseEntity<>(adress, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
