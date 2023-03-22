/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Controllers;

import com.test.firstSpringApp.Entities.Book;
import com.test.firstSpringApp.Services.BookService;
import com.test.firstSpringApp.Services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.firstSpringApp.validationGroups.EntitiesWithoutId;
import org.springframework.http.MediaType;

/**
 *
 * @author papar
 */
@RestController
@RequestMapping("/books")
@Validated(EntitiesWithoutId.class)
public class BookController {

    private CategoryService cs;
    private BookService bs;

    public BookController(BookService bs, CategoryService cs) {
        this.bs = bs;
        this.cs = cs;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bs.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(
            @PathVariable @Min(value = 1, message = "Id should be greater that 0") int id
    ) {
        Optional<Book> book = bs.getBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(book.get());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createBook(@RequestBody @Valid Book b) {
        if (!cs.existCategoryById(b.getCategoryId())) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                    .body("{\"message\":\"The category with id"+b.getCategoryId()+" doesn't exist\"}");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(bs.createBook(b));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(
            @PathVariable @Min(value = 1, message = "id should be greater that 0") int id,
            @RequestBody @Valid Book b
    ) {
        b.setId(id);
        try {
            return ResponseEntity.ok(bs.updateBook(b));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(
            @PathVariable @Min(value = 1, message = "id should be greater that 0") int id
    ) {
        bs.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
