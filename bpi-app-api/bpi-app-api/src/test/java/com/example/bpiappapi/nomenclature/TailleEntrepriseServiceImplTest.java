package com.example.bpiappapi.nomenclature;

import com.example.bpiappapi.nomenclature.model.TailleEntreprise;
import com.example.bpiappapi.nomenclature.repository.TailleEntrepriseRepository;
import com.example.bpiappapi.nomenclature.service.impl.TailleEntrepriseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

  class TailleEntrepriseServiceImplTest {

    @Mock
    private TailleEntrepriseRepository tailleEntrepriseRepository;

    @InjectMocks
    private TailleEntrepriseServiceImpl tailleEntrepriseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTailleEntreprises() {
        TailleEntreprise taille1 = new TailleEntreprise(1L,1, "Small");
        TailleEntreprise taille2 = new TailleEntreprise(2L,2, "Medium");
        List<TailleEntreprise> tailles = Arrays.asList(taille1, taille2);

        when(tailleEntrepriseRepository.findAll()).thenReturn(tailles);

        List<TailleEntreprise> result = tailleEntrepriseService.getAllTailleEntreprises();
        assertEquals(tailles, result);
    }

    @Test
    void testSaveAllTailleEntreprises() {
        TailleEntreprise taille1 = new TailleEntreprise(1L, 1,"Small");
        TailleEntreprise taille2 = new TailleEntreprise(2L,2, "Medium");
        List<TailleEntreprise> tailles = Arrays.asList(taille1, taille2);

        tailleEntrepriseService.saveAllTailleEntreprises(tailles);
        verify(tailleEntrepriseRepository).saveAll(tailles);
    }
}

