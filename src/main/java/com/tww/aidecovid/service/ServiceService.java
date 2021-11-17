package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Service;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ServiceService {

    List<Service> getAllServices();
    Service getService(String id);
    void addService(Service service);
    void updateService(Long id, Service service);
    void deleteService(Long id);

    Page<Service> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);



}
