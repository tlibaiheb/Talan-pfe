package com.example.bpiappapi.pm.services;

import com.example.bpiappapi.pm.models.AddressForm;

public interface AddressService {
    AddressForm createAddress(Long organisationPId, AddressForm addressForm);
    public AddressForm getAdressById(Long id);
}

