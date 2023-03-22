/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Controllers;

import com.test.firstSpringApp.Entities.Author;
import com.test.firstSpringApp.Services.AuthorService;
import com.test.firstSpringApp.validationGroups.EntitiesWithoutId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@Validated(EntitiesWithoutId.class)
public class AuthorController {

    private AuthorService as;

    public AuthorController(AuthorService as) {
        this.as = as;
    }

    @GetMapping()
    public ResponseEntity<List<Author>> getAuthors(@RequestParam Optional<String> keyword) {
        if (keyword.isEmpty()) {
            return ResponseEntity.ok(as.getAllAuthors());
        } else {
            return ResponseEntity.ok(as.getAuthorsByKeyWord(keyword.get().trim()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(
            @PathVariable @Min(1) int id
    ) {
        Optional<Author> res = as.getAuthorById(id);
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(res.get());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createAuthor(@RequestBody @Valid Author a) {
        Optional<Author> res = as.createAuthor(a);
        if (res.isEmpty()) {
            return ResponseEntity.internalServerError()
                    .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                    .body("{\"message\":\"There was an unexpected error while creating author\"}");
        } else {

            return ResponseEntity.created(URI.create("/authors/" + res.get().getId()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(res.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable @Min(1) int id,
            @Valid @RequestBody Author a
    ) {

        a.setId(id);
        Optional<Author> res = as.updateAuthor(a);
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(res.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(
            @PathVariable @Min(1) int id
    ) {
        as.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}
