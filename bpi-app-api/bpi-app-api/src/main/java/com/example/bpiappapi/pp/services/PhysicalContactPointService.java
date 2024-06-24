package com.example.bpiappapi.pp.services;

import com.example.bpiappapi.pp.models.PhysicalContactPoint;

public interface PhysicalContactPointService {
    PhysicalContactPoint createPhysicalContactPoint(Long personPId, PhysicalContactPoint physicalContactPoint);
    public PhysicalContactPoint getAdressById(Long id);


}
