package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Message;
import com.tww.aidecovid.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository repository;

    @Override
    public Optional<Message> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Message message) {
        repository.save(message);
    }

    @Override
    public void delete(Message message) {
        repository.delete(message);
    }
}
