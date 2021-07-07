package com.week2sprint.demo.contollers;



import com.week2sprint.demo.View.CountTodo;
import com.week2sprint.demo.models.Todos;
import com.week2sprint.demo.services.TodosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController
{
    @Autowired
    TodosServices todoService;

    @GetMapping(value = "/todos",
            produces = {"application/json"})
    public ResponseEntity<?> listAllTodos()
    {
        List<Todos> allTodos = todoService.findAll();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }


    @GetMapping(value = "/todos/{todoId}",
            produces = {"application/json"})
    public ResponseEntity<?> getTodoById(@PathVariable Long Todoid)
    {
        Todos t = todoService.findTodoById(Todoid);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> findQuoteByUserName(
            @PathVariable
                    String userName)
    {
        List<Todos> theTodos = todoService.findByUserName(userName);
        return new ResponseEntity<>(theTodos, HttpStatus.OK);
    }


    @GetMapping(value = "/todoscount",
            produces = {"application/json"})
    public ResponseEntity<?> getTodosCount()
    {
        ArrayList<CountTodo> myList = todoService.getCountQuotes();
        myList.sort((q1, q2) -> q1.getUsername().compareToIgnoreCase(q2.getUsername()));
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }


    @PostMapping(value = "/todo")
    public ResponseEntity<?> addNewTodo(@Valid @RequestBody Todos newTodo) throws URISyntaxException
    {
        newTodo = todoService.save(newTodo);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{todosid}").buildAndExpand(newTodo.getTodoid()).toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/todo/{Todosid}")
    public ResponseEntity<?> updateQuote(
            @RequestBody Todos updateTodo,
            @PathVariable long Todosid)
    {
        todoService.update(updateTodo, Todosid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteQuoteById(
            @PathVariable
                    long id)
    {
        todoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
