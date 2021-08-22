package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Service;

import java.util.Optional;

public interface ServiceService {

    Optional<Service> getById(Long id);

    void save(Service service);

    void delete(Service service);
}
