package com.week2sprint.demo.repos;

import com.week2sprint.demo.View.CountTodo;
import com.week2sprint.demo.models.Todos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TodoRepo extends CrudRepository<Todos, Long>
{
    @Query(value = "SELECT u.username, count(q.quotesid) as countquotes FROM quotes q JOIN users u on q.userid = u.userid GROUP BY u.username", nativeQuery = true)
    ArrayList<CountTodo> getCountQuotes();
}
