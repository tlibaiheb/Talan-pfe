package com.example.bpiappapi.api;

import com.example.bpiappapi.dtos.AddressForm;
import com.example.bpiappapi.dtos.CompanyFilledDTO;
import com.example.bpiappapi.nomenclature.model.FranceLocations;
import com.example.bpiappapi.nomenclature.model.Naf;
import com.example.bpiappapi.nomenclature.model.TailleEntreprise;
import com.example.bpiappapi.nomenclature.repository.FranceLocationRepository;
import com.example.bpiappapi.nomenclature.repository.NafCodeRepository;
import com.example.bpiappapi.nomenclature.repository.TailleEntrepriseRepository;
import com.example.bpiappapi.notification.Notification;
import com.example.bpiappapi.notification.NotificationRepository;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import com.example.bpiappapi.pm.reposotories.OrganisationPartyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SirenService {
    private final RestTemplate restTemplate;
    //  private final String apiKey = "gIzbTf2yEvMeDiaX96gO15mzHp9byGHo";

    private final ObjectMapper objectMapper;
    private final TailleEntrepriseRepository tailleEntrepriseRepository;
    private final NafCodeRepository nafCodeRepository;
    private final FranceLocationRepository FranceLocationRepository;
    private final OrganisationPartyRepository organisationPartyRepository;
    private final NotificationRepository notificationRepository;

    @Value("${siren.api.key}")
    private String apiKey;

    public CompanyFilledDTO getEtablissementData(String siren) throws JsonProcessingException {
        String url = "https://data.siren-api.fr//v3/unites_legales/" + siren;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Client-Secret", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Extraire la valeur de l'attribut "denomination"
        String responseBody = response.getBody();

        JsonNode jsonNode = objectMapper.readTree(responseBody);

        CompanyFilledDTO result = new CompanyFilledDTO();
        result.setLegalName(jsonNode.get("unite_legale").get("denomination").asText());
        String label = jsonNode.get("unite_legale").get("categorie_entreprise").asText();
        System.out.println("label" + label);
        TailleEntreprise tailleEntreprise = tailleEntrepriseRepository.findByLabel(label);
        System.out.println("tailleEntreprise" + tailleEntreprise);
        if (tailleEntreprise != null) {
            result.setEuropeanOrganisationPartySizeGId(tailleEntreprise.getGId());
        }

        //NAF CODE CLEANNING AND MAPPING
        String activitePrincipale = jsonNode.get("unite_legale").get("activite_principale").asText();
        activitePrincipale = activitePrincipale.replace(".", "");
        Naf naf = nafCodeRepository.findByCode(activitePrincipale);
        result.setNafCodeGId(naf.getGId());

        result.setDate_dernier_traitement(jsonNode.get("unite_legale").get("date_dernier_traitement").asText());
        result.setLegalCategoryGId(jsonNode.get("unite_legale").get("categorie_juridique").asText());
        //date creation
        result.setCreationDate(jsonNode.get("unite_legale").get("date_creation").asText());
        //date enregistrement
        result.setRegistrationDate(jsonNode.get("unite_legale").get("date_debut").asText());


        AddressForm adresse = new AddressForm();
        adresse.setNum(jsonNode.get("unite_legale").get("etablissement_siege").get("numero_voie").asText());
        adresse.setLibelleDeLaVoie(jsonNode.get("unite_legale").get("etablissement_siege").get("libelle_voie").asText());
        adresse.setTypeDeVoie(jsonNode.get("unite_legale").get("etablissement_siege").get("type_voie").asText());


        String codePostal = jsonNode.get("unite_legale").get("etablissement_siege").get("code_postal").asText();
        FranceLocations FranceLocations = FranceLocationRepository.findByPostCode(codePostal);
        System.out.println("FranceLocations" + FranceLocations);
        if (FranceLocations != null) {
            adresse.setFrLocationGId(FranceLocations.getLocationGId());
        }
        result.setAddressForm(adresse);

        return result;


    }

    /*@Scheduled(cron = "**0**")
    public void notification() throws JsonProcessingException {
        List<OrganisationPartyRequest> organisationPartyRequestList = organisationPartyRepository.findAll();
        if(organisationPartyRequestList!= null){
            for (OrganisationPartyRequest organisationPartyRequest : organisationPartyRequestList){
                CompanyFilledDTO companyFilledDTO = getEtablissementData(organisationPartyRequest.getSiren());
               if (companyFilledDTO!=null){

                   if (!companyFilledDTO.getDate_dernier_traitement().equals(organisationPartyRequest.getDate_dernier_traitement())){

                       Notification notification = new Notification() ;
                       notification.setIdPm(organisationPartyRequest.getId());
                       notification.setExecuted(false);
                       notificationRepository.save(notification);


                   }

               }


            }
        }
     }*/
    public boolean checkForUpdates(String siren) throws JsonProcessingException {
        CompanyFilledDTO companyFilledDTO = getEtablissementData(siren);
        OrganisationPartyRequest organisationPartyRequest = organisationPartyRepository.findBySiren(siren);

        // Comparer les dates de dernier traitement
        return !companyFilledDTO.getDate_dernier_traitement().equals(organisationPartyRequest.getDate_dernier_traitement());
    }

    @Scheduled(fixedRate = 120000)
    public void notification() throws JsonProcessingException {
        Iterable<OrganisationPartyRequest> organisationPartyRequestList = organisationPartyRepository.findAll();
        for (OrganisationPartyRequest organisationPartyRequest : organisationPartyRequestList) {
            String siren = organisationPartyRequest.getSiren();
            System.out.println("siren:"+siren);
            if (siren != null && !siren.isEmpty()) {
                    if (checkForUpdates(siren)) {
                        // Créer une nouvelle notification
                        if (!notificationRepository.existsByIdPm(organisationPartyRequest.getId())) {
                            Notification notification = new Notification();
                            notification.setSiren(organisationPartyRequest.getSiren());
                            notification.setIdPm(organisationPartyRequest.getId());
                            System.out.println("notification:"+notification);
                            notificationRepository.save(notification);
                        }

                    }else {
                        System.out.println("pas de modification pou :"+siren);
                    }
            }else{
                System.out.println("siren vide");

            }
        }
    }






}
