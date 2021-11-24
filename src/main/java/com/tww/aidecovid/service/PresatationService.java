package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Prestation;
import com.tww.aidecovid.model.Service;

import java.util.List;
import java.util.Optional;


public interface PresatationService {

    Optional<Prestation> getById(Long id);

    List<Prestation> getAvailablePrestationsByServiceId(Service service);

    void save(Prestation prestation);

    void delete(Prestation prestation);
}
