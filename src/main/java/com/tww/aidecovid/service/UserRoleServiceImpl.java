package com.tww.aidecovid.service;

import com.tww.aidecovid.model.UserRole;
import com.tww.aidecovid.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    private UserRoleRepository userRoleRepository;

    public void addUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }


}
