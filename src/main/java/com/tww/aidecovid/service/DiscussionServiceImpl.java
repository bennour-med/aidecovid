package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Discussion;
import com.tww.aidecovid.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private DiscussionRepository repository;

    @Override
    public Optional<Discussion> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Discussion discussion) {
    repository.save(discussion);
    }

    @Override
    public void delete(Discussion discussion) {
    repository.delete(discussion);
    }
}
