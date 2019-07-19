package com.week2sprint.demo.services;

import com.week2sprint.demo.View.CountQuotes;
import com.week2sprint.demo.models.Todos;

import java.util.ArrayList;
import java.util.List;

public interface TodosServices
{
    List<Todos> findAll();

    Todos findQuoteById(long id);

    List<Todos> findByUserName (String username);

    void delete(long id);

    Todos save(Todos todos);

    Todos update(Todos todos, long id);

    ArrayList<CountQuotes> getCountQuotes();
}
