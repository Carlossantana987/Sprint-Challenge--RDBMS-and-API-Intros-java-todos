package com.week2sprint.demo.services;

import com.week2sprint.demo.models.Role;

import java.util.List;

public interface RoleServices
{
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);
}
