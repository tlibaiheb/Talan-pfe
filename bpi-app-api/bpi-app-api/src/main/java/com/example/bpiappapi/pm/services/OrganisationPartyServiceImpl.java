package com.example.bpiappapi.pm.services;

import com.example.bpiappapi.pm.models.AddressForm;
import com.example.bpiappapi.pm.models.OrganisationPartyIdentification;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import com.example.bpiappapi.pm.reposotories.OrganisationPartyRepository;
import com.example.bpiappapi.pm.reposotories.OrganistaionIdentif;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrganisationPartyServiceImpl implements OrganisationPartyService {
    private static final Logger logger = LoggerFactory.getLogger(OrganisationPartyServiceImpl.class);

    private final OrganisationPartyRepository organisationPartyRepository;
    private OrganistaionIdentif organisationIdentificationRepository;

    @Autowired
    public OrganisationPartyServiceImpl(OrganisationPartyRepository organisationPartyRepository,OrganistaionIdentif organisationIdentificationRepository) {
        this.organisationPartyRepository = organisationPartyRepository;
        this.organisationIdentificationRepository=organisationIdentificationRepository;
    }


    //ajout
    @Override
    @Transactional
    public OrganisationPartyRequest createOrganisationParty(OrganisationPartyRequest request) {
        try {
            request.setIdTiers(request.generateIdTiers());
            OrganisationPartyRequest createdParty = organisationPartyRepository.save(request);
            logger.info("Organisation Party created successfully: {}", createdParty);
            if(createdParty.getOrganisationPartyIdentifications()!= null ){
            for (OrganisationPartyIdentification identification : createdParty.getOrganisationPartyIdentifications()) {
                identification.setOrganisationPartyRequest(createdParty);
            }}
            return organisationPartyRepository.save(createdParty);
        } catch (Exception e) {
            logger.error("Failed to create Organisation Party", e);
            throw e;
        }
    }




    //delete
    @Override
    @Transactional
    public void deleteOrganisationParty(Long id) {
        try {
            OrganisationPartyRequest existingParty = getOrganisationPartyById(id);
        organisationPartyRepository.delete(existingParty);
        } catch (Exception e) {
            logger.error("Failed to create Organisation Party", e);
            throw e;
        }
    }



    //get by id
    @Override
    @Transactional
    public OrganisationPartyRequest getOrganisationPartyById(Long id) {
        try {

            return organisationPartyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organisation Party not found with id: " + id));
        } catch (Exception e) {
            logger.error("Failed to create Organisation Party", e);
            throw e;
        }
    }



    //update
    @Override
    @Transactional
    public OrganisationPartyRequest updateOrganisationParty(Long id, OrganisationPartyRequest request) throws NotFoundException {
        OrganisationPartyRequest existingParty = getOrganisationPartyById(id);
        if (existingParty == null) {
            throw new NotFoundException("Organisation Party not found with id: " + id);
        }

        try {
            existingParty.setCP(request.getCP());
            existingParty.setTradingName(request.getTradingName());
            existingParty.setCreationDate(request.getCreationDate());
            existingParty.setEuropeanOrganisationPartySizeGId(request.getEuropeanOrganisationPartySizeGId());
            existingParty.setGicsCodeGId(request.getGicsCodeGId());
            existingParty.setLegalCategoryGId(request.getLegalCategoryGId());
            existingParty.setLegalName(request.getLegalName());
            existingParty.setMissingSIRENSIRETJustificationGId(request.getMissingSIRENSIRETJustificationGId());
            existingParty.setNafCodeGId(request.getNafCodeGId());
            existingParty.setRegistrationDate(request.getRegistrationDate());
            existingParty.setShareCapital(request.getShareCapital());

            // Copie de l'addressForm
            if (request.getAddressForm() != null) {
                AddressForm existingAddressForm = existingParty.getAddressForm();
                if (existingAddressForm == null) {
                    existingAddressForm = new AddressForm();
                    existingParty.setAddressForm(existingAddressForm);
                }
                // Copie des propriétés de l'addressForm
                existingAddressForm.setNum(request.getAddressForm().getNum());
                existingAddressForm.setTypeDeVoie(request.getAddressForm().getTypeDeVoie());
                existingAddressForm.setLibelleDeLaVoie(request.getAddressForm().getLibelleDeLaVoie());
                existingAddressForm.setComplementAdresse(request.getAddressForm().getComplementAdresse());
                existingAddressForm.setFrLocationGId(request.getAddressForm().getFrLocationGId());
            }

            // Copie de organisationPartyIdentifications
            if (request.getOrganisationPartyIdentifications() != null) {
                List<OrganisationPartyIdentification> existingIdentifications1 = existingParty.getOrganisationPartyIdentifications();
                existingParty.getOrganisationPartyIdentifications().clear();
                for (OrganisationPartyIdentification identification : existingIdentifications1) {
                    organisationIdentificationRepository.delete(identification);
                }
                List<OrganisationPartyIdentification> existingIdentifications = existingParty.getOrganisationPartyIdentifications();
            for (OrganisationPartyIdentification identification : request.getOrganisationPartyIdentifications()) {
                    OrganisationPartyIdentification existingIdentification = new OrganisationPartyIdentification();
                    existingIdentification.setOrganisationPartyIdentificationTypeGId(identification.getOrganisationPartyIdentificationTypeGId());
                    existingIdentification.setOrganisationPartyIdentificationValue(identification.getOrganisationPartyIdentificationValue());
                    existingIdentification.setStartingDate(identification.getStartingDate());
                    existingIdentification.setClosingDate(identification.getClosingDate());
                    existingIdentification.setOrganisationPartyRequest(existingParty);
                    //existingIdentifications.add(identification);
                   identification.setOrganisationPartyRequest(existingParty);
                   organisationIdentificationRepository.save(existingIdentification);
                   existingParty.getOrganisationPartyIdentifications().add(identification);


                }









            }

            return organisationPartyRepository.save(existingParty);
        } catch (Exception e) {
            logger.error("Failed to update Organisation Party", e);
            throw new RuntimeException("Internal server error occurred", e);

        }
    }




    //get all
    @Override
    @Transactional(readOnly = true)
    public List <OrganisationPartyRequest> getAllOrganisationParties() {
        try {
            return organisationPartyRepository.findAll();
        } catch (Exception e) {
            logger.error("Failed to retrieve all Organisation Parties", e);
            throw e;
        }
    }




    @Override
    @Transactional(readOnly = true)
    public List<OrganisationPartyRequest> filtrerListe(String selectedOption, String searchValue) {
        try {
            List<OrganisationPartyRequest> organisationParties = organisationPartyRepository.findAll();

            // Filtrer les données en fonction de selectedOption et searchValue
            if ("SIREN".equals(selectedOption)) {
                // Filtrer par SIREN
                organisationParties = organisationParties.stream()
                        .filter(item -> item.getSiren() != null && item.getSiren().contains(searchValue))
                        .collect(Collectors.toList());
            } else if ("RaisonSocial".equals(selectedOption)) {
                // Filtrer par Raison sociale (Nom légal)
                organisationParties = organisationParties.stream()
                        .filter(item -> item.getLegalName() != null && item.getLegalName().contains(searchValue))
                        .collect(Collectors.toList());
            } else if ("idTiers".equals(selectedOption)) {
                // Filtrer par ID tiers
                Long idTiers = Long.parseLong(searchValue);
                Optional<OrganisationPartyRequest> partyOptional = organisationParties.stream()
                        .filter(item -> item.getIdTiers() != null && item.getIdTiers().contains(searchValue))
                        .findFirst();
                return partyOptional.map(List::of).orElse(List.of());
            } else if ("NuméroCP".equals(selectedOption)) {
                // Filtrer par Numéro CP
                organisationParties = organisationParties.stream()
                        .filter(item -> item.getCP() != null && item.getCP().contains(searchValue))
                        .collect(Collectors.toList());
            }

            return organisationParties;
        } catch (Exception e) {
            logger.error("Failed to filter Organisation Parties", e);
            throw e;
        }
    }



    @Override
    @Transactional(readOnly = true)
    public boolean existsBySiren(String siren) {
        return organisationPartyRepository.existsBySiren(siren);
    }





    @Override
    @Transactional(readOnly = true)
    public OrganisationPartyRequest getBySiren(String siren) {
        try {
            OrganisationPartyRequest organisationParty = organisationPartyRepository.findBySiren(siren);
            if (organisationParty == null) {
                throw new NotFoundException("Organisation Party not found with SIREN: " + siren);
            }
            return organisationParty;
        } catch (Exception e) {
            logger.error("Failed to retrieve Organisation Party by SIREN", e);
            throw new RuntimeException("Internal server error occurred", e);
        }
    }







}


















