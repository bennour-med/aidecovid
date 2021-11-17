package com.tww.aidecovid.service;

import com.tww.aidecovid.model.User;
import com.tww.aidecovid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        repository.findAll().forEach(users::add);

        return users;
    }

    public User getUser(String id) {
        int indice = Integer.parseInt(id);

        return repository.findById(indice);
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void updateUser(Long id, User user) {
        repository.save(user);
    }

    public void deleteUser(Long id) {
         repository.deleteById(id);
    }

    public Page<User> findPaginatedUser(int pageNoUser, int pageSizeUser, String sortFieldUser, String sortDirectionUser) {
        Sort sortUser = sortDirectionUser.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortFieldUser).ascending() :
                Sort.by(sortFieldUser).descending();

        Pageable pageableUser = PageRequest.of(pageNoUser - 1, pageSizeUser, sortUser);

        return this.repository.findAll(pageableUser);
    }
}