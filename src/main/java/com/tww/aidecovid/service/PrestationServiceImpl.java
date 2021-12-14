package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Prestation;
import com.tww.aidecovid.model.User;
import com.tww.aidecovid.repository.PrestationRepository;
import com.tww.aidecovid.security.AuthenticationFacade;
import com.tww.aidecovid.statics.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestationServiceImpl implements PresatationService{

    @Autowired
    private PrestationRepository repository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public Optional<Prestation> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Prestation> getAvailablePrestationsByServiceId(com.tww.aidecovid.model.Service service) {
        return repository.findByServiceAndStatus(service, Status.NEW.getValue());
    }

    @Override
    public List<Prestation> getProvidedPrestations(User provider, List<String> statusList) {
        return repository.findByProviderAndStatusIn(provider, statusList);
    }

    @Override
    public List<Prestation> getRequestedPrestations(User requester, List<String> statusList) {
        return repository.findByRequesterAndStatusIn(requester, statusList);
    }

    @Override
    public String updateNotifCount() {
        String notifCount = "";
        try {
            long count = getNotificationCount(authenticationFacade.getAuthenticatedUser());
            if (count > 0) notifCount+=count;
        } catch (Exception ex) {

        }
        return notifCount;
    }

    @Override
    public long getNotificationCount(User user) {
        return repository.countByProviderAndStatus(user, Status.WAITING.getValue())
                + repository.countByRequesterAndStatus(user, Status.APPROVED.getValue());
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
