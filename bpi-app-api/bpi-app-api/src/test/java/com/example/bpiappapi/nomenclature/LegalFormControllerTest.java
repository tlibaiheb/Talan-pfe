package com.example.bpiappapi.nomenclature;

import com.example.bpiappapi.nomenclature.controller.LegalFormController;
import com.example.bpiappapi.nomenclature.model.LegalForms;
import com.example.bpiappapi.nomenclature.service.LegalFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LegalFormControllerTest {

    @Mock
    private LegalFormService legalFormService;

    @InjectMocks
    private LegalFormController legalFormController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllLegalForms() {
        // Arrange
        List<LegalForms> expectedLegalForms = Arrays.asList(
                new LegalForms(1L, 1, "gId1", "Label1"),
                new LegalForms(2L, 2, "gId2", "Label2")
        );
        when(legalFormService.getAllLegalForms()).thenReturn(expectedLegalForms);

        // Act
        List<LegalForms> actualLegalForms = legalFormController.getAllLegalForms();

        // Assert
        assertEquals(expectedLegalForms, actualLegalForms);
        verify(legalFormService, times(1)).getAllLegalForms();
    }
}

