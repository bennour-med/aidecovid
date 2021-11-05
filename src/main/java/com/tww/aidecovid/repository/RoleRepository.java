package com.tww.aidecovid.repository;

import com.tww.aidecovid.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository  extends CrudRepository<Role, Long> {

    Role findByRole(String role);
}
