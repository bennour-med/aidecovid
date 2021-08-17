package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Message;

import java.util.Optional;

public interface MessageService {
    Optional<Message> getById(Long id);

    void save(Message message);

    void delete(Message message);
}
