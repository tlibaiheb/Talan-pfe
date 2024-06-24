package com.example.bpiappapi.nomenclature;

import com.example.bpiappapi.nomenclature.controller.MissingJustificationsController;
import com.example.bpiappapi.nomenclature.model.MissingSiretSirenJustifications;
import com.example.bpiappapi.nomenclature.service.MissingJustificationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MissingJustificationsControllerTest {

    @Mock
    private MissingJustificationsService missingJustificationsService;

    @InjectMocks
    private MissingJustificationsController missingJustificationsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllMissing() {
        // Arrange
        List<MissingSiretSirenJustifications> expectedMissing = Arrays.asList(
                new MissingSiretSirenJustifications(1L, 1, "Label1"),
                new MissingSiretSirenJustifications(2L, 2, "Label2")
        );
        when(missingJustificationsService.getAllMissing()).thenReturn(expectedMissing);

        // Act
        List<MissingSiretSirenJustifications> actualMissing = missingJustificationsController.getAllMissing();

        // Assert
        assertEquals(expectedMissing, actualMissing);
        verify(missingJustificationsService, times(1)).getAllMissing();
    }
}

