package com.example.bpiappapi.nomenclature.utils;
import com.example.bpiappapi.nomenclature.model.*;
import com.example.bpiappapi.nomenclature.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class DataLoader {
    private final CountryService countryService;
    private final FranceLocationService franceLocationService;
    private final GicsCodeService gicsCodeService;
    private final LegalCategoryService legalCategoryService;
    private final LegalFormService legalFormService;
    private final MissingJustificationsService missingJustificationsService;
    private final NafCodeService nafCodeService;
    private final NamePrefixService namePrefixService;
    private final PersonRoleTypeService personRoleTypeService;
    private final PhysicalContactTypeService physicalContactTypeService;
    private final TailleEntrepriseService tailleEntrepriseService;
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    @Autowired
    public DataLoader(
            CountryService countryService,
            FranceLocationService franceLocationService,
            GicsCodeService gicsCodeService,
            LegalCategoryService legalCategoryService,
            LegalFormService legalFormService ,
            MissingJustificationsService missingJustificationsService ,
            NafCodeService nafCodeService ,
            NamePrefixService namePrefixService ,
            PersonRoleTypeService personRoleTypeService,
            PhysicalContactTypeService physicalContactTypeService,
            TailleEntrepriseService tailleEntrepriseService
    )
    {
        this.countryService = countryService;
        this.franceLocationService = franceLocationService;
        this.gicsCodeService = gicsCodeService;
        this.legalCategoryService=legalCategoryService;
        this.legalFormService=legalFormService;
        this.missingJustificationsService=missingJustificationsService;
        this.nafCodeService=nafCodeService;
        this.namePrefixService=namePrefixService;
        this.personRoleTypeService=personRoleTypeService;
        this.physicalContactTypeService=physicalContactTypeService;
        this.tailleEntrepriseService=tailleEntrepriseService;
    }
    @PostConstruct
    public void loadCountries() {
        if (countryService.getAllCountries().isEmpty()) {
            try {
                logger.info("Loading countries data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/countries.json");
                List<Country> countries = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                countryService.saveAllCountries(countries);
                logger.info("Countries Saved!");
            } catch (Exception e) {
                logger.error("Error loading countries data: {}", e.getMessage());
            }
        } else {
            logger.info("Countries data already loaded. Skipping loading.");
        }
    }
    @PostConstruct
    public void loadFranceLocations() {
        if (franceLocationService.getAllLocations().isEmpty()) {
            try {
                logger.info("Loading France locations data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<FranceLocations>> typeReference = new TypeReference<List<FranceLocations>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/france-locations.json");
                List<FranceLocations> franceLocations = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                franceLocationService.saveAllLocations(franceLocations);
                logger.info("France locations Saved!");
            } catch (Exception e) {
                logger.error("Error loading France locations data: {}", e.getMessage());
            }
        } else {
            logger.info("France locations data already loaded. Skipping loading.");
        }
    }
    @PostConstruct
    public void loadGicsCodes() {
        if (gicsCodeService.getAllGicsCodes().isEmpty()) {
            try {
                logger.info("Loading GICS codes data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<GicsCodes>> typeReference = new TypeReference<List<GicsCodes>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/gics-codes.json");
                List<GicsCodes> gicsCodes = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                gicsCodeService.saveAllGicsCodes(gicsCodes);
                logger.info("GICS codes Saved!");
            } catch (Exception e) {
                logger.error("Error loading GICS codes data: {}", e.getMessage());
            }
        } else {
            logger.info("GICS codes data already loaded. Skipping loading.");
        }
    }
    @PostConstruct
    public void loadLegalCategories() {
        if (legalCategoryService.getAllLegalCategories().isEmpty()) {
            try {
                logger.info("Loading legal categories data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<LegalCategories>> typeReference = new TypeReference<List<LegalCategories>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/legal-categories.json");
                List<LegalCategories> legalCategories = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                legalCategoryService.saveAllLegalCategories(legalCategories);
                logger.info("Legal categories Saved!");
            } catch (Exception e) {
                logger.error("Error loading legal categories data: {}", e.getMessage());
            }
        } else {
            logger.info("Legal categories data already loaded. Skipping loading.");
        }
    }
    @PostConstruct
    public void loadLegalForms() {
        if (legalFormService.getAllLegalForms().isEmpty()) {
            try {
                logger.info("Loading legal forms data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<LegalForms>> typeReference = new TypeReference<List<LegalForms>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/legal-forms.json");
                List<LegalForms> legalForms = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                legalFormService.saveAllLegalForms(legalForms);
                logger.info("Legal forms Saved!");
            } catch (Exception e) {
                logger.error("Error loading legal forms data: {}", e.getMessage());
            }
        } else {
            logger.info("Legal forms data already loaded. Skipping loading.");
        }
    }
    @PostConstruct
    public void loadMissingJustifications() {
        if (missingJustificationsService.getAllMissing().isEmpty()) {
            try {
                logger.info("Loading missing justifications data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<MissingSiretSirenJustifications>> typeReference = new TypeReference<List<MissingSiretSirenJustifications>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/missing-siren-siret-justifications.json");
                List<MissingSiretSirenJustifications> missingJustifications = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                missingJustificationsService.saveAllMissingJustifications(missingJustifications);
                logger.info("Missing justifications Saved!");
            } catch (Exception e) {
                logger.error("Error loading missing justifications data: {}", e.getMessage());
            }
        } else {
            logger.info("Missing justifications data already loaded. Skipping loading.");
        }
    }

    @PostConstruct
    public void loadNafCodes() {
        if (nafCodeService.getAllNafCodes().isEmpty()) {
            try {
                logger.info("Loading NAF codes data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Naf>> typeReference = new TypeReference<List<Naf>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/naf-codes.json");
                List<Naf> nafCodes = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                nafCodeService.saveAllNafCodes(nafCodes);
                logger.info("NAF codes Saved!");
            } catch (Exception e) {
                logger.error("Error loading NAF codes data: {}", e.getMessage());
            }
        } else {
            logger.info("NAF codes data already loaded. Skipping loading.");
        }
    }
    @PostConstruct
    public void loadNamePrefixes() {
        if (namePrefixService.getAllNamePrefixes().isEmpty()) {
            try {
                logger.info("Loading name prefixes data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<NamePrefixes>> typeReference = new TypeReference<List<NamePrefixes>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/name-prefixes.json");
                List<NamePrefixes> namePrefixes = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                namePrefixService.saveAllNamePrefixes(namePrefixes);
                logger.info("Name prefixes Saved!");
            } catch (Exception e) {
                logger.error("Error loading name prefixes data: {}", e.getMessage());
            }
        } else {
            logger.info("Name prefixes data already loaded. Skipping loading.");
        }
    }
    @PostConstruct
    public void loadPersonRoleTypes() {
        if (personRoleTypeService.getAllPersonRoleTypes().isEmpty()) {
            try {
                logger.info("Loading person role types data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<PersonRoleTypes>> typeReference = new TypeReference<List<PersonRoleTypes>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/person-role-types.json");
                List<PersonRoleTypes> personRoleTypes = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                personRoleTypeService.saveAllPersonRoleTypes(personRoleTypes);
                logger.info("Person role types Saved!");
            } catch (Exception e) {
                logger.error("Error loading person role types data: {}", e.getMessage());
            }
        } else {
            logger.info("Person role types data already loaded. Skipping loading.");
        }
    }
    @PostConstruct
    public void loadPhysicalContactPointTypes() {
        if (physicalContactTypeService.getAllPhysicalContactTypes().isEmpty()) {
            try {
                logger.info("Loading physical contact point types data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<PhysicalContactPointType>> typeReference = new TypeReference<List<PhysicalContactPointType>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/physical-contact-point-types.json");
                List<PhysicalContactPointType> physicalContactPointTypes = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                physicalContactTypeService.saveAllPhysicalContactPointTypes(physicalContactPointTypes);
                logger.info("Physical contact point types Saved!");
            } catch (Exception e) {
                logger.error("Error loading physical contact point types data: {}", e.getMessage());
            }
        } else {
            logger.info("Physical contact point types data already loaded. Skipping loading.");
        }
    }
    @PostConstruct
    public void loadTailleEntreprises() {
        if (tailleEntrepriseService.getAllTailleEntreprises().isEmpty()) {
            try {
                logger.info("Loading taille entreprise data...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<TailleEntreprise>> typeReference = new TypeReference<List<TailleEntreprise>>() {};
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/european-organisation-party-sizes.json");
                List<TailleEntreprise> tailleEntreprises = mapper.readValue(new InputStreamReader(inputStream), typeReference);
                tailleEntrepriseService.saveAllTailleEntreprises(tailleEntreprises);
                logger.info("Taille entreprise Saved!");
            } catch (Exception e) {
                logger.error("Error loading taille entreprise data: {}", e.getMessage());
            }
        } else {
            logger.info("Taille entreprise data already loaded. Skipping loading.");
        }
    }



}
