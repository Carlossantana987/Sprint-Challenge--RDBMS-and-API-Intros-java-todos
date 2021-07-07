package com.week2sprint.demo;

import com.week2sprint.demo.models.Role;
import com.week2sprint.demo.models.Todos;
import com.week2sprint.demo.models.User;
import com.week2sprint.demo.models.UserRoles;
import com.week2sprint.demo.repos.RoleRepo;
import com.week2sprint.demo.repos.TodoRepo;
import com.week2sprint.demo.repos.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

@Transactional
@Component
public class SeedDatas implements CommandLineRunner
{
    RoleRepo rolerepos;
    UserRepo userrepos;
    TodoRepo todorepos;

    public SeedDatas(RoleRepo rolerepos, UserRepo userrepos, TodoRepo todorepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.todorepos = todorepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        rolerepos.save(r1);
        rolerepos.save(r2);

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u1 = new User("barnbarn", "ILuvM4th!", users);
        u1.getTodos().add(new Todos("Finish java-orders-swagger", u1, true));
        u1.getTodos().add(new Todos("Feed the turtles", u1, true));
        u1.getTodos().add(new Todos("Complete the sprint challege", u1,false));
        userrepos.save(u1);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        User u2 = new User("admin", "password", admins);
        u2.getTodos().add(new Todos("Walk the dogs", u2, false));
        u2.getTodos().add(new Todos("provide feedback to my instructor", u2, true));
        userrepos.save(u2);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("Bob", "password", users);
        userrepos.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Jane", "password", users);
        userrepos.save(u4);
    }
}

