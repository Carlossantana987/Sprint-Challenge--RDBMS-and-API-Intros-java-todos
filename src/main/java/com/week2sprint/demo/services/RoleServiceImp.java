package com.week2sprint.demo.services;

import com.week2sprint.demo.models.Role;
import com.week2sprint.demo.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service(value = "roleService")
public class RoleServiceImp implements RoleServices
{
    @Autowired
    RoleRepo rolerepos;

    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Role findRoleById(long id)
    {
        return rolerepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Override
    public void delete(long id)
    {
        rolerepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        rolerepos.deleteById(id);
    }


    @Transactional
    @Override
    public Role save(Role role)
    {
        return rolerepos.save(role);
    }
}

