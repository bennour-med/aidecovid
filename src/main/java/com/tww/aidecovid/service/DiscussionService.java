package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Discussion;

import java.util.Optional;

public interface DiscussionService {

    Optional<Discussion> getById(Long id);

    void save(Discussion discussion);

    void delete(Discussion discussion);
}
