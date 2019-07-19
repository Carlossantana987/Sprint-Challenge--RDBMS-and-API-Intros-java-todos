package com.week2sprint.demo.services;

//import org.apache.catalina.User;

import com.week2sprint.demo.models.User;

import java.util.List;

public interface UserServices
{
    List<User> findAll();

    User findUserByName(String name);

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User update(User user, long id);
}
