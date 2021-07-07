package com.week2sprint.demo.repos;

//import org.apache.catalina.User;
import com.week2sprint.demo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
