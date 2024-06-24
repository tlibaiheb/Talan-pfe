package com.example.bpiappapi.nomenclature;

import com.example.bpiappapi.nomenclature.controller.CountryController;
import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.service.CountryService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
 class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void testGetAllCountries() throws Exception {
        // Mock des données
        Country country1 = Country.create("FR", "France", "FR_CODE", "FR", "FRA", "French", "250", "+33");
        Country country2 = Country.create("US", "United States", "US_CODE", "US", "USA", "American", "840", "+1");
        List<Country> countries = Arrays.asList(country1, country2);

        // Mock du service
        when(countryService.getAllCountries()).thenReturn(countries);

        // Effectuer la requête et vérifier la réponse
        mockMvc.perform(get("/glossaries/countries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].countryGId").value("FR"))
                .andExpect(jsonPath("$[0].countryLabel").value("France"))
                .andExpect(jsonPath("$[0].internalBpiCode").value("FR_CODE"))
                .andExpect(jsonPath("$[0].iso2Code").value("FR"))
                .andExpect(jsonPath("$[0].iso3Code").value("FRA"))
                .andExpect(jsonPath("$[0].nationalityLabel").value("French"))
                .andExpect(jsonPath("$[0].numericalCode").value("250"))
                .andExpect(jsonPath("$[0].phoneCountryCode").value("+33"))
                .andExpect(jsonPath("$[1].countryGId").value("US"))
                .andExpect(jsonPath("$[1].countryLabel").value("United States"))
                .andExpect(jsonPath("$[1].internalBpiCode").value("US_CODE"))
                .andExpect(jsonPath("$[1].iso2Code").value("US"))
                .andExpect(jsonPath("$[1].iso3Code").value("USA"))
                .andExpect(jsonPath("$[1].nationalityLabel").value("American"))
                .andExpect(jsonPath("$[1].numericalCode").value("840"))
                .andExpect(jsonPath("$[1].phoneCountryCode").value("+1"));
    }
}
