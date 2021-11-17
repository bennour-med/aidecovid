package com.tww.aidecovid.repository;

import com.tww.aidecovid.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findByLastname(String lastname);
    User findById(long id);
    User findByLogin(String login);
    User findByEmail(String email);

}