package com.tww.aidecovid.service;

import com.tww.aidecovid.model.User;

import java.util.List;

public interface UserService  {

    List<User> getAllUsers();
    User getUser(String id);
    void addUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);

}
