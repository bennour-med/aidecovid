package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Service;
import com.tww.aidecovid.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository repository;

    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();

        repository.findAll().forEach(services::add);

        return services;
    }

    public Service getService(String id) {
        int indice = Integer.parseInt(id);

        return repository.findById(indice);
    }

    public void addService(Service service) {
        repository.save(service);
    }

    public void updateService(Long id, Service Service) {
        repository.save(Service);
    }

    public void deleteService(Long id) {
        repository.deleteById(id);
    }

    public Page<Service> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.repository.findAll(pageable);
    }
}
