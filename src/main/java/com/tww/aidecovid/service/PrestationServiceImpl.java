package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Prestation;
import com.tww.aidecovid.repository.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrestationServiceImpl implements PresatationService{

    @Autowired
    private PrestationRepository repository;

    @Override
    public Optional<Prestation> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Prestation prestation) {
        repository.save(prestation);
    }

    @Override
    public void delete(Prestation prestation) {
        repository.delete(prestation);
    }
}
