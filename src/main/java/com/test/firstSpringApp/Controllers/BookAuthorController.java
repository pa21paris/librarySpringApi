/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.firstSpringApp.Entities.BookAuthor;
import com.test.firstSpringApp.Services.BookAuthorService;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author papar
 */
@RestController
@RequestMapping("/bookauthor")
public class BookAuthorController {
    private BookAuthorService baService;
    
    public BookAuthorController(BookAuthorService baService){
        this.baService=baService;
    }
    
    @PostMapping("/{bookId}/{authorId}")
    public ResponseEntity<String> addAuthorToBook(@PathVariable int bookId, @PathVariable int authorId){
        ObjectMapper om=new ObjectMapper();
        Optional<BookAuthor> res=baService.addAuthorToBook(bookId, authorId);   
        if (res.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"There was an error adding that author to the book\"}");
        }else{
            try {
                return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{ \"idBook\":\""+res.get().getId().getIdBook()+"\","
                            + "\"idAuthor\":\""+res.get().getId().getIdAuthor()+"\"}");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }
    }
    
    @DeleteMapping("/{bookId}/{authorId}")
    public ResponseEntity removeAuthorToBook(@PathVariable int bookId, @PathVariable int authorId){
        baService.removeAuthorToBook(bookId, authorId);
        return ResponseEntity.noContent().build();
    }
}
