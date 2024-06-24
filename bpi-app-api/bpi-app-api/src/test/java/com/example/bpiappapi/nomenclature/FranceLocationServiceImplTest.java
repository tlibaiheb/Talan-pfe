package com.example.bpiappapi.nomenclature;


import com.example.bpiappapi.nomenclature.model.FranceLocations;
import com.example.bpiappapi.nomenclature.repository.FranceLocationRepository;
import com.example.bpiappapi.nomenclature.service.FranceLocationService;
import com.example.bpiappapi.nomenclature.service.impl.FranceLocationServiceImpl;
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

  class FranceLocationServiceImplTest {

    @Mock
    private FranceLocationRepository franceLocationRepository;

    @InjectMocks
    private FranceLocationServiceImpl franceLocationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLocations() {
        FranceLocations location1 = new FranceLocations(1L, "GID1", "Location 1", "12345");
        FranceLocations location2 = new FranceLocations(2L, "GID2", "Location 2", "67890");
        List<FranceLocations> locations = Arrays.asList(location1, location2);

        when(franceLocationRepository.findAll()).thenReturn(locations);

        List<FranceLocations> result = franceLocationService.getAllLocations();
        assertEquals(locations, result);
    }

    @Test
    void testSaveAllLocations() {
        FranceLocations location1 = new FranceLocations(1L, "GID1", "Location 1", "12345");
        FranceLocations location2 = new FranceLocations(2L, "GID2", "Location 2", "67890");
        List<FranceLocations> locations = Arrays.asList(location1, location2);

        franceLocationService.saveAllLocations(locations);
        verify(franceLocationRepository).saveAll(locations);
    }
}
