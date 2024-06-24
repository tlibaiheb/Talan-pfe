package com.example.bpiappapi.api;

import com.example.bpiappapi.dtos.CompanyFilledDTO;
import com.example.bpiappapi.notification.Notification;
import com.example.bpiappapi.notification.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class SirenControllerTest {

    @Mock
    private SirenService sirenService;

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private SirenController sirenController;

    @Test
     void testGetEtablissementData() throws JsonProcessingException {
        String siren = "123456789";
        CompanyFilledDTO companyFilledDTO = new CompanyFilledDTO();
        companyFilledDTO.setLegalName("Test Company");

        when(sirenService.getEtablissementData(siren)).thenReturn(companyFilledDTO);

        CompanyFilledDTO result = sirenController.getEtablissementData(siren);

        assertEquals("Test Company", result.getLegalName());
    }

    @Test
     void testCheckForUpdates_MiseAJourDisponible() throws JsonProcessingException {
        String siren = "123456789";

        when(sirenService.checkForUpdates(siren)).thenReturn(true);

        String result = sirenController.checkForUpdates(siren);

        assertEquals("Mise à jour disponible", result);
    }

    @Test
     void testCheckForUpdates_MiseAJourNonDisponible() throws JsonProcessingException {
        String siren = "123456789";

        when(sirenService.checkForUpdates(siren)).thenReturn(false);

        String result = sirenController.checkForUpdates(siren);

        assertEquals("Mise à jour non disponible", result);
    }


}
