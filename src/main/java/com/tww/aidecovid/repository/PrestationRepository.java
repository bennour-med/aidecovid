package com.tww.aidecovid.repository;

import com.tww.aidecovid.model.Prestation;
import com.tww.aidecovid.model.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestationRepository extends CrudRepository<Prestation, Long> {

    List<Prestation> findByServiceAndStatus(Service service, String status);
}
