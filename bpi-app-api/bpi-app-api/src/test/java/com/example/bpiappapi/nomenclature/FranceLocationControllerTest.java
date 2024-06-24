package com.example.bpiappapi.nomenclature;



 import com.example.bpiappapi.nomenclature.controller.FranceLocationController;
 import com.example.bpiappapi.nomenclature.model.FranceLocations;
import com.example.bpiappapi.nomenclature.service.FranceLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FranceLocationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FranceLocationService franceLocationService;

    @InjectMocks
    private FranceLocationController franceLocationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(franceLocationController).build();
    }

    @Test
    public void testGetAllLocations() throws Exception {
        // Mock data
        FranceLocations location1 = new FranceLocations(1L,"1","Paris", "Ile-de-France");
        FranceLocations location2 = new FranceLocations(2L,"2","Marseille", "Provence-Alpes-Côte d'Azur");
        List<FranceLocations> locations = Arrays.asList(location1, location2);

        // Mock service method call
        when(franceLocationService.getAllLocations()).thenReturn(locations);

        List<FranceLocations> response = franceLocationController.getAllLocations();
        assertEquals(locations, response);
    }





}


