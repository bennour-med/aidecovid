package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Prestation;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface PresatationService {

    Optional<Prestation> getById(Long id);

    void save(Prestation prestation);

    void delete(Prestation prestation);
}
