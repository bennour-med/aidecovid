package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Prestation;
import com.tww.aidecovid.model.Service;
import com.tww.aidecovid.model.User;

import java.util.List;
import java.util.Optional;


public interface PresatationService {

    Optional<Prestation> getById(Long id);

    List<Prestation> getAvailablePrestationsByServiceId(Service service);

    List<Prestation> getProvidedPrestations(User provider, List<String> statusList);

    List<Prestation> getRequestedPrestations(User requester, List<String> statusList);

    long getNotificationCount(User user);

    void save(Prestation prestation);

    void delete(Prestation prestation);
}
