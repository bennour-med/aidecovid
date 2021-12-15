package com.tww.aidecovid.repository;

import com.tww.aidecovid.model.Prestation;
import com.tww.aidecovid.model.Service;
import com.tww.aidecovid.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestationRepository extends CrudRepository<Prestation, Long> {

    List<Prestation> findByProviderIsNotAndServiceAndStatus(User provider,Service service, String status);
    List<Prestation> findByProviderAndStatusIn(User provider, List<String> statusList);
    List<Prestation> findByRequesterAndStatusIn(User requester, List<String> statusList);
    long countByProviderAndStatus(User provider, String status);
    long countByRequesterAndStatus(User requester, String status);

}
