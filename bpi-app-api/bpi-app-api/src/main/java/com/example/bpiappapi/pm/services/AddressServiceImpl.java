package com.example.bpiappapi.pm.services;

import com.example.bpiappapi.pm.models.AddressForm;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import com.example.bpiappapi.pm.reposotories.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bpiappapi.pm.reposotories.OrganisationPartyRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger logger = LoggerFactory.getLogger(OrganisationPartyServiceImpl.class);

    private final AddressRepository addressRepository;
    private final OrganisationPartyRepository organisationPartyRequestRepository;

    @Autowired
    public AddressServiceImpl( AddressRepository addressRepository, OrganisationPartyRepository organisationPartyRequestRepository) {
        this.addressRepository = addressRepository;
        this.organisationPartyRequestRepository = organisationPartyRequestRepository;

    }

    @Override
    public AddressForm createAddress(Long organisationPId, AddressForm addressForm) {
        OrganisationPartyRequest organisationPartyRequest = organisationPartyRequestRepository.findById(organisationPId)
                .orElseThrow(() -> new RuntimeException("OrganisationPartyRequest not found with id: " + organisationPId));

        addressForm.setOrganisationPartyRequest(organisationPartyRequest);

        try {
            return addressRepository.save(addressForm);
        } catch (Exception e) {
            logger.error("Failed to create address", e);
            throw e;
        }
    }


    @Override
    @Transactional
    public AddressForm getAdressById(Long id) {
        try {

            return addressRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("address not found with id: " + id));
        } catch (Exception e) {
            logger.error("Failed to create address", e);
            throw e;
        }
    }
}
