package com.example.bpiappapi.api;

import com.example.bpiappapi.dtos.CompanyFilledDTO;
import com.example.bpiappapi.notification.Notification;
import com.example.bpiappapi.notification.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SirenController {

    private final SirenService sirenService;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    public SirenController(SirenService sirenService) {
        this.sirenService = sirenService;
    }

    @GetMapping("/api/v1/etablissements/{siren}")
    public CompanyFilledDTO getEtablissementData(@PathVariable String siren) throws JsonProcessingException {
        return sirenService.getEtablissementData(siren);
    }

    @GetMapping("/api/v1/notifications/{siren}")
    public String checkForUpdates(@PathVariable String siren) throws JsonProcessingException {
        if (sirenService.checkForUpdates(siren)) {
            return "Mise à jour disponible";
        } else {
            return "Mise à jour non disponible";
        }
    }




    @GetMapping ("/api/v1/getnotifications")
    public  ResponseEntity<List<Notification>> getAllNotifications() {
        return new ResponseEntity<>(notificationRepository.findAll(), HttpStatus.OK);
    }
}
