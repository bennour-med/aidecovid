package com.tww.aidecovid.repository;

import com.tww.aidecovid.model.Prestation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestationRepository extends CrudRepository<Prestation, Long> {
}
