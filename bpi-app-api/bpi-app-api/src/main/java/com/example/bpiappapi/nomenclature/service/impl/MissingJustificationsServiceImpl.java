package com.example.bpiappapi.nomenclature.service.impl;

import com.example.bpiappapi.nomenclature.model.MissingSiretSirenJustifications;
import com.example.bpiappapi.nomenclature.repository.MissingJustificationsRepository;
import com.example.bpiappapi.nomenclature.service.MissingJustificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissingJustificationsServiceImpl implements MissingJustificationsService {
    private final MissingJustificationsRepository missingJustificationsRepository;

    @Autowired
    public MissingJustificationsServiceImpl(MissingJustificationsRepository missingJustificationsRepository) {
        this.missingJustificationsRepository = missingJustificationsRepository;
    }

    @Override
    public List<MissingSiretSirenJustifications> getAllMissing() {
        return missingJustificationsRepository.findAll();
    }

    @Override
    public void saveAllMissingJustifications(List<MissingSiretSirenJustifications> missingJustifications) {
        missingJustificationsRepository.saveAll(missingJustifications);

    }


}
