package com.example.bpiappapi.nomenclature.utils;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.FranceLocations;
import com.example.bpiappapi.nomenclature.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class DataLoaderTest {

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private Logger logger;
    @Mock
    private CountryService countryService;
    @Mock
    private FranceLocationService franceLocationService;
    @Mock
    private  GicsCodeService gicsCodeService;
    @Mock
    private  LegalCategoryService legalCategoryService;
    @Mock
    private  LegalFormService legalFormService;
    @Mock
    private  MissingJustificationsService missingJustificationsService;
    @Mock
    private  NafCodeService nafCodeService;
    @Mock
    private  NamePrefixService namePrefixService;
    @Mock
    private  PersonRoleTypeService personRoleTypeService;
    @Mock
    private  PhysicalContactTypeService physicalContactTypeService;
    @Mock
    private  TailleEntrepriseService tailleEntrepriseService;

    @InjectMocks
    private DataLoader dataLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadCountries() {
        when(countryService.getAllCountries()).thenReturn(Collections.emptyList());

        dataLoader.loadCountries();

        verify(countryService).getAllCountries();
        verify(countryService).saveAllCountries(anyList());
    }


    @Test
    void testLoadFranceLocations() {
        when(franceLocationService.getAllLocations()).thenReturn(Collections.emptyList());

        dataLoader.loadFranceLocations();

        verify(franceLocationService).getAllLocations();
        verify(franceLocationService).saveAllLocations(anyList());
    }

    @Test
    void testLoadGicsCodes(){
        when(gicsCodeService.getAllGicsCodes()).thenReturn(Collections.emptyList());
        dataLoader.loadGicsCodes();
        verify(gicsCodeService).getAllGicsCodes();
        verify(gicsCodeService).saveAllGicsCodes(anyList());
    }


    @Test
    void testloadLegalCategories(){
        when(legalCategoryService.getAllLegalCategories()).thenReturn(Collections.emptyList());
        dataLoader.loadLegalCategories();
        verify(legalCategoryService).getAllLegalCategories();
        verify(legalCategoryService).saveAllLegalCategories(anyList());
    }


    @Test
    void testloadLegalForms(){
        when(legalFormService.getAllLegalForms()).thenReturn(Collections.emptyList());
        dataLoader.loadLegalForms();
        verify(legalFormService).getAllLegalForms();
        verify((legalFormService)).saveAllLegalForms(anyList());
    }
    @Test
    void testloadMissingJustifications(){
        when(missingJustificationsService.getAllMissing()).thenReturn(Collections.emptyList());
        dataLoader.loadMissingJustifications();
        verify(missingJustificationsService).getAllMissing();
        verify((missingJustificationsService)).saveAllMissingJustifications(anyList());
    }


    @Test
    void testloadNafCodes(){
        when(nafCodeService.getAllNafCodes()).thenReturn(Collections.emptyList());
        dataLoader.loadNafCodes();
        verify(nafCodeService).getAllNafCodes();
        verify((nafCodeService)).saveAllNafCodes(anyList());
    }

    @Test
    void testloadNamePrefixes(){
        when(namePrefixService.getAllNamePrefixes()).thenReturn(Collections.emptyList());
        dataLoader.loadNamePrefixes();
        verify(namePrefixService).getAllNamePrefixes();
        verify((namePrefixService)).saveAllNamePrefixes(anyList());
    }

    @Test
    void testloadPersonRoleTypes(){
        when(personRoleTypeService.getAllPersonRoleTypes()).thenReturn(Collections.emptyList());
        dataLoader.loadPersonRoleTypes();
        verify(personRoleTypeService).getAllPersonRoleTypes();
        verify((personRoleTypeService)).saveAllPersonRoleTypes(anyList());
    }

    @Test
    void testloadPhysicalContactPointTypes(){
        when(physicalContactTypeService.getAllPhysicalContactTypes()).thenReturn(Collections.emptyList());
        dataLoader.loadPhysicalContactPointTypes();
        verify(physicalContactTypeService).getAllPhysicalContactTypes();
        verify((physicalContactTypeService)).saveAllPhysicalContactPointTypes(anyList());
    }


    @Test
    void testloadTailleEntreprises(){
        when(tailleEntrepriseService.getAllTailleEntreprises()).thenReturn(Collections.emptyList());
        dataLoader.loadTailleEntreprises();
        verify(tailleEntrepriseService).getAllTailleEntreprises();
        verify((tailleEntrepriseService)).saveAllTailleEntreprises(anyList());
    }







}
