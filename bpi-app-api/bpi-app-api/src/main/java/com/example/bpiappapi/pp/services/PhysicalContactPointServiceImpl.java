package com.example.bpiappapi.pp.services;
import com.example.bpiappapi.pp.models.PersonParties;
import com.example.bpiappapi.pp.models.PhysicalContactPoint;
import com.example.bpiappapi.pp.repositories.PersonPartiesRepository;
import com.example.bpiappapi.pp.repositories.PhysicalContactPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhysicalContactPointServiceImpl implements PhysicalContactPointService {
    private final PhysicalContactPointRepository physicalContactPointRepository;
    private final PersonPartiesRepository personPartiesRepository;

    @Autowired
    public PhysicalContactPointServiceImpl(PhysicalContactPointRepository physicalContactPointRepository, PersonPartiesRepository personPartiesRepository) {
        this.physicalContactPointRepository = physicalContactPointRepository;
        this.personPartiesRepository = personPartiesRepository;
    }

    @Override
    public PhysicalContactPoint createPhysicalContactPoint(Long personPId, PhysicalContactPoint physicalContactPoint) {
        PersonParties personParties = personPartiesRepository.findById(personPId)
                .orElseThrow(() -> new RuntimeException("Person-parties not found with id: " + personPId));
        physicalContactPoint.setPersonParties(personParties);

        boolean isInFrance = physicalContactPoint.isInFrance();




        if (isInFrance) {
            // Personne en France, ajouter les attributs appropriés de l'adresse
            physicalContactPoint.setComplementAdresse(physicalContactPoint.getComplementAdresse());
            physicalContactPoint.setNum(physicalContactPoint.getNum());
            physicalContactPoint.setInFrance(isInFrance);
            physicalContactPoint.setTypeDeVoie(physicalContactPoint.getTypeDeVoie());
            physicalContactPoint.setLibelleDeLaVoie(physicalContactPoint.getLibelleDeLaVoie());
            physicalContactPoint.setComplementAdresse(physicalContactPoint.getComplementAdresse());
            physicalContactPoint.setFrLocationGId(physicalContactPoint.getFrLocationGId());
            physicalContactPoint.setPhysicalContactPointTypeGId(physicalContactPoint.getPhysicalContactPointTypeGId());
        } else {
            // Personne non en France, ajouter les attributs appropriés de l'adresse
            physicalContactPoint.setAddress1(physicalContactPoint.getAddress1());
            physicalContactPoint.setAddress2(physicalContactPoint.getAddress2());
            physicalContactPoint.setAddress3(physicalContactPoint.getAddress3());
            physicalContactPoint.setAddress4(physicalContactPoint.getAddress4());
            physicalContactPoint.setCountryGId(physicalContactPoint.getCountryGId());
            physicalContactPoint.setOutOfFrTown(physicalContactPoint.getOutOfFrTown());
            physicalContactPoint.setOutOfFrPostCode(physicalContactPoint.getOutOfFrPostCode());
            physicalContactPoint.setPhysicalContactPointTypeGId(physicalContactPoint.getPhysicalContactPointTypeGId());
            physicalContactPoint.setRegionOutFr(physicalContactPoint.getRegionOutFr());
        }


        try {
            return physicalContactPointRepository.save(physicalContactPoint);
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    @Transactional
    public PhysicalContactPoint getAdressById(Long id) {
        try {

            return physicalContactPointRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("address not found with id: " + id));
        } catch (Exception e) {
            throw e;
        }
    }

}

