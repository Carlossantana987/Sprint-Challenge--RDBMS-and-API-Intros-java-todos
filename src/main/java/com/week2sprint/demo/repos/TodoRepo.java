package com.week2sprint.demo.repos;

import com.week2sprint.demo.models.Todos;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<Todos, long>
{

}
