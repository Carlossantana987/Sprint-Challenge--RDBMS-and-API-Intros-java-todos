package com.week2sprint.demo.services;





import com.week2sprint.demo.View.CountTodo;
import com.week2sprint.demo.models.Todos;
import com.week2sprint.demo.repos.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "quoteService")
public class TodosServiceImp implements TodosServices
{
    @Autowired
    private TodoRepo todorepos;

    @Override
    public ArrayList<CountTodo> getCountQuotes()
    {
        return todorepos.getCountQuotes();
    }

    @Override
    public List<Todos> findAll()
    {
        List<Todos> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Todos findTodoById(long id)
    {
        return todorepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (todorepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (todorepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                todorepos.deleteById(id);
            } else
            {
                throw new EntityNotFoundException(Long.toString(id) + " " + authentication.getName());
            }
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Todos save(Todos todo)
    {
        return todorepos.save(todo);
    }

    @Override
    public List<Todos> findByUserName(String username)
    {
        List<Todos> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }

    @Override
    public Todos update(Todos todo, long id)
    {
        Todos newTodo = todorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (todo.getTodo() != null)
        {
            newTodo.setTodo(todo.getTodo());
        }

        if (todo.getUser() != null)
        {
            newTodo.setUser(todo.getUser());
        }

        return todorepos.save(newTodo);
    }
}
