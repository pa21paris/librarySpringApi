/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Controllers;

import com.test.firstSpringApp.Entities.Book;
import com.test.firstSpringApp.Services.BookService;
import com.test.firstSpringApp.Services.CategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author papar
 */
@RestController
@RequestMapping("/books")
public class BookController {
    
    private CategoryService cs;
    private BookService bs;
    
    public BookController(BookService bs, CategoryService cs){
        this.bs=bs;
        this.cs=cs;
    }
    
    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bs.getAllBooks());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        Optional<Book> book=bs.getBookById(id);
        if(book.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(book.get());
        }
    }
    
    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book b){
        if(b.getId()!=0 || b.getCategoryId()==0){
            return ResponseEntity.badRequest().build();
        }else if(!cs.existCategoryById(b.getCategoryId())){
            return ResponseEntity.badRequest().build();
        }else{
            
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(bs.createBook(b));
                
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book b){
        b.setId(id);
        if(id<0){
            return ResponseEntity.badRequest().build();
        }else{
            try {
                return ResponseEntity.ok(bs.updateBook(b));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseEntity.notFound().build();
            }
        }
    }
    
}
