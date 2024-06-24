package com.example.bpiappapi.pp.services;

import com.example.bpiappapi.pp.models.PersonParties;
import com.example.bpiappapi.pp.models.PhysicalContactPoint;
import com.example.bpiappapi.pp.repositories.PersonPartiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class PersonPartiesServiceImpl implements PersonPartiesService {
    private final PersonPartiesRepository personPartiesRepository;

    @Autowired
    public PersonPartiesServiceImpl(PersonPartiesRepository personPartiesRepository) {
        this.personPartiesRepository = personPartiesRepository;
    }

    @Override
    public PersonParties createPersonParties(PersonParties personParties) {


        try {
            // Générer l'idTiers
            personParties.setIdTiers(generateRandomFiveDigitId());
            PersonParties savedPersonParties = personPartiesRepository.save(personParties);
            return savedPersonParties;
        } catch (Exception e) {
            // Log the exception or perform any necessary error handling
            throw new RuntimeException("Error saving person parties", e); // Rethrow with custom message
        }
    }

    private String generateRandomFiveDigitId() {
        int min = 10000;
        int max = 99999;
        return String.valueOf((int) (Math.random() * (max - min + 1)) + min);
    }

    @Override
    @Transactional
    public PersonParties getPersonPartiesById(Long id) {
        try {

            return personPartiesRepository.findById(id).orElseThrow(() -> new RuntimeException("person Party not found with id: " + id));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public PersonParties updatePersonParties(Long id, PersonParties personParties) {
        try {
            PersonParties existingParty = personPartiesRepository.findById(id).orElseThrow(() -> new RuntimeException("Person party not found with id: " + id));

            // Générer l'idTiers si nécessaire
            if (existingParty.getIdTiers() == null || existingParty.getIdTiers().isEmpty()) {
                existingParty.setIdTiers(generateRandomFiveDigitId());
            }
            existingParty.setNamePrefixGId(personParties.getNamePrefixGId());
            existingParty.setBirthName(personParties.getBirthName());
            existingParty.setMainFirstName(personParties.getMainFirstName());
            existingParty.setFirstNames(personParties.getFirstNames());
            existingParty.setBirthDate(personParties.getBirthDate());
            existingParty.setCountryOfBirthGId(personParties.getCountryOfBirthGId());
            existingParty.setUseName(personParties.getUseName());
            existingParty.setCpNumber(personParties.getCpNumber());
            existingParty.setOutOfFrBirthPostCode(personParties.getOutOfFrBirthPostCode());
            existingParty.setOutOfFrPlaceOfBirth(personParties.getOutOfFrPlaceOfBirth());
            existingParty.setFrPlaceOfBirthGId(personParties.getFrPlaceOfBirthGId());
            if (personParties.getPhysicalContactPoint() != null) {
                PhysicalContactPoint existingAddress = existingParty.getPhysicalContactPoint();
                if (existingAddress == null) {
                    existingAddress = new PhysicalContactPoint();
                    existingParty.setPhysicalContactPoint(existingAddress);
                }
                existingAddress.setAddress1(personParties.getPhysicalContactPoint().getAddress1());
                existingAddress.setAddress2(personParties.getPhysicalContactPoint().getAddress2());
                existingAddress.setAddress3(personParties.getPhysicalContactPoint().getAddress3());
                existingAddress.setAddress4(personParties.getPhysicalContactPoint().getAddress4());
                existingAddress.setFrLocationGId(personParties.getPhysicalContactPoint().getFrLocationGId());
                existingAddress.setCountryGId(personParties.getPhysicalContactPoint().getCountryGId());
                existingAddress.setOutOfFrTown(personParties.getPhysicalContactPoint().getOutOfFrTown());
                existingAddress.setOutOfFrPostCode(personParties.getPhysicalContactPoint().getOutOfFrPostCode());
                existingAddress.setPhysicalContactPointTypeGId(personParties.getPhysicalContactPoint().getPhysicalContactPointTypeGId());
                existingAddress.setNum(personParties.getPhysicalContactPoint().getNum());
                existingAddress.setTypeDeVoie(personParties.getPhysicalContactPoint().getTypeDeVoie());
                existingAddress.setLibelleDeLaVoie(personParties.getPhysicalContactPoint().getLibelleDeLaVoie());
                existingAddress.setRegionOutFr(personParties.getPhysicalContactPoint().getRegionOutFr());
                existingAddress.setComplementAdresse(personParties.getPhysicalContactPoint().getComplementAdresse());

                existingAddress.setPersonParties(existingParty);
            }


            //PersonParties updatedParty = personPartiesRepository.save(existingParty);
            return personPartiesRepository.save(existingParty);
        } catch (Exception e) {
            throw e;
        }
    }


    //get all
    @Override
    @Transactional(readOnly = true)
    public List<PersonParties> getAllPersonParties() {
        try {
            return personPartiesRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonParties> getPersonPartiesByCpNumber(String cpNumber) {
        try {
            List<PersonParties> res = new ArrayList<>();
            List<PersonParties> personParties = personPartiesRepository.findAll();
            for (PersonParties personParty : personParties) {
                if (personParty.getCpNumber().startsWith(cpNumber)) {
                    res.add(personParty);
                }
            }
            return res;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonParties> getPersonPartiesByBirthName(String birthName) {
        try {
            List<PersonParties> res = new ArrayList<>();

//            List<PersonParties> personParties = personPartiesRepository.findAll();
//            for (PersonParties personParty : personParties) {
//                if (personParty.getBirthName().toLowerCase().startsWith(birthName.toLowerCase())) {
//                    res.add(personParty);
//                }
//            }
            return personPartiesRepository.findAllByBirthNameIgnoreCaseStartingWith(birthName);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonParties> getPersonPartiesByMainFirstName(String mainFirstName) {
        try {
            List<PersonParties> res = new ArrayList<>();
            //  List<PersonParties> personParties = personPartiesRepository.findAll();
            //  for (PersonParties personParty : personParties) {
            //     if (personParty.getMainFirstName().startsWith(mainFirstName)) {
            //       res.add(personParty);
            //       }
            //  }
            return personPartiesRepository.findAllByMainFirstNameIgnoreCaseStartingWith(mainFirstName);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonParties> getPersonPartiesByIdTiers(String idTiers) {
        try {
            List<PersonParties> res = new ArrayList<>();
            List<PersonParties> personParties = personPartiesRepository.findAll();
            for (PersonParties personParty : personParties) {
                if (personParty.getIdTiers().startsWith(idTiers)) {
                    res.add(personParty);
                }
            }
            return res;
        } catch (Exception e) {
            throw e;
        }
    }


}


















