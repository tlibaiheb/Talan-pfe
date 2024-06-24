package com.example.bpiappapi.nomenclature;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.repository.CountryRepository;
import com.example.bpiappapi.nomenclature.service.impl.CountryServiceImpl;
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

  class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryServiceImpl countryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCountries() {
        Country country1 = new Country(1L, "GID1", "Country 1", "BPI1", "ISO2-1", "ISO3-1", "Nationality 1", "Num1", "Phone1");
        Country country2 = new Country(2L, "GID2", "Country 2", "BPI2", "ISO2-2", "ISO3-2", "Nationality 2", "Num2", "Phone2");
        List<Country> countries = Arrays.asList(country1, country2);

        when(countryRepository.findAll()).thenReturn(countries);

        List<Country> result = countryService.getAllCountries();
        assertEquals(countries, result);
    }

    @Test
    void testSaveAllCountries() {
        Country country1 = new Country(1L, "GID1", "Country 1", "BPI1", "ISO2-1", "ISO3-1", "Nationality 1", "Num1", "Phone1");
        Country country2 = new Country(2L, "GID2", "Country 2", "BPI2", "ISO2-2", "ISO3-2", "Nationality 2", "Num2", "Phone2");
        List<Country> countries = Arrays.asList(country1, country2);

        countryService.saveAllCountries(countries);
        verify(countryRepository).saveAll(countries);
    }
}

