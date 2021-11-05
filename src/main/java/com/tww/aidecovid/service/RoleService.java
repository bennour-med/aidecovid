package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAll();

    Role get(String id);

    void add(Role role);

    void update(String id, Role role);

    void delete(String id);
}
