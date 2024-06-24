package com.example.bpiappapi.nomenclature.service;

import com.example.bpiappapi.nomenclature.model.Country;
import com.example.bpiappapi.nomenclature.model.MissingSiretSirenJustifications;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MissingJustificationsService {
    List<MissingSiretSirenJustifications> getAllMissing();
    void saveAllMissingJustifications(List<MissingSiretSirenJustifications> missingJustifications);
}
