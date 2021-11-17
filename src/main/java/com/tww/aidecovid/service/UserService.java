package com.tww.aidecovid.service;

import com.tww.aidecovid.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService  {

    List<User> getAllUsers();
    User getUser(String id);
    void addUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);

    Page<User> findPaginatedUser(int pageNoUser, int pageSizeUser, String sortFieldUser, String sortDirectionUser);

}
