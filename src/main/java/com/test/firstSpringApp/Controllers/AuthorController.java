/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.firstSpringApp.Entities.Author;
import com.test.firstSpringApp.Services.AuthorService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author papar
 */
@RestController
@RequestMapping("/authors")
public class AuthorController {
    
    private AuthorService as;
    
    public AuthorController(AuthorService as){
        this.as=as;
    }
    
    @GetMapping()
    public ResponseEntity<List<Author>> getAuthors(@RequestParam Optional<String> keyword){
        if(keyword.isEmpty()){
            return ResponseEntity.ok(as.getAllAuthors());
        }else{
            return ResponseEntity.ok(as.getAuthorsByKeyWord(keyword.get().trim()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id){
        Optional<Author> res=as.getAuthorById(id);
        if(res.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(res.get());
        }
    }
    
    @PostMapping()
    public ResponseEntity<String> createAuthor(@RequestBody Author a){
        if(a.getId()!=0 || a.getFirstName()==null || a.getLastName()==null){
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                    .body("{\"message\":"
                            + "\"Request body shouldn't have id and must have first and last name\"}");
        }else{
            Optional<Author> res=as.createAuthor(a);
            if(res.isEmpty()){
                return ResponseEntity.internalServerError()
                        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                        .body("{\"message\":\"There was an unexpected error while creating author\"}");
            }else{
                try {
                    ObjectMapper om=new ObjectMapper();
                    return ResponseEntity.created(URI.create("/authors/"+res.get().getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(om.writeValueAsString(res.get()));
                } catch (Exception e) {
                    return ResponseEntity.internalServerError()
                            .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                            .body("{\"message\":\"Couldn't transform object to JSON\"}");
                }
                
            }
        }
    }
    
}