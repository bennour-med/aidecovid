package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Service;
import com.tww.aidecovid.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private ServiceRepository repository;

    @Override
    public Optional<Service> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Service service) {
        repository.save(service);
    }

    @Override
    public void delete(Service service) {
        repository.delete(service);
    }
}
