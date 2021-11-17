package com.tww.aidecovid.repository;

import com.tww.aidecovid.model.Service;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {

    Service findById(long id);

}

