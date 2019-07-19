package com.week2sprint.demo.contollers;



import com.week2sprint.demo.View.CountQuotes;
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
@RequestMapping("/quotes")
public class TodoController
{
    @Autowired
    TodosServices quoteService;

    @GetMapping(value = "/quotes",
            produces = {"application/json"})
    public ResponseEntity<?> listAllQuotes()
    {
        List<Todos> allQuotes = quoteService.findAll();
        return new ResponseEntity<>(allQuotes, HttpStatus.OK);
    }


    @GetMapping(value = "/quote/{quoteId}",
            produces = {"application/json"})
    public ResponseEntity<?> getQuote(
            @PathVariable
                    Long quoteId)
    {
        Todos q = quoteService.findQuoteById(quoteId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> findQuoteByUserName(@PathVariable String userName)
    {
        List<Todos> theQuotes = quoteService.findByUserName(userName);
        return new ResponseEntity<>(theQuotes, HttpStatus.OK);
    }


    @GetMapping(value = "/quotescount",
            produces = {"application/json"})
    public ResponseEntity<?> getQuotesCount()
    {
        ArrayList<CountQuotes> myList = quoteService.getCountQuotes();
        myList.sort((q1, q2) -> q1.getUsername().compareToIgnoreCase(q2.getUsername()));
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }


    @PostMapping(value = "/quote")
    public ResponseEntity<?> addNewQuote(@Valid @RequestBody Todos newQuote) throws URISyntaxException
    {
        newTodo = TodosServices.save(newTodo);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{quoteid}").buildAndExpand(newQuote.getQuotesid()).toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/todo/{todoId}")
    public ResponseEntity<?> updateQuote(@RequestBody Todos updateTodos, @PathVariable long quoteid)
    {
        quoteService.update(updateTodos, quoteid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/Todo/{id}")
    public ResponseEntity<?> deleteQuoteById(@PathVariable long id)
    {
        quoteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
