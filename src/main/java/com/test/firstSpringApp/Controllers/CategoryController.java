/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Controllers;

import com.test.firstSpringApp.Services.CategoryService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.firstSpringApp.Entities.Category;
import com.test.firstSpringApp.validationGroups.EntitiesWithoutId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author papar
 */
@RestController
@RequestMapping("/categories")
@Validated(EntitiesWithoutId.class)
public class CategoryController {

    @Autowired
    private CategoryService cs;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam Optional<String> keyword) {
        if (keyword.isEmpty()) {
            try {
                return ResponseEntity.ok(cs.getAllCategories());
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.ok(cs.getCategoryByKeyWord(keyword.get()));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable @Min(1) int id) {
        try {
            return ResponseEntity.ok(cs.getCategoryById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createCategory(@RequestBody @Valid Category newCategory) {
        try {
            newCategory = cs.createNewCategory(newCategory);
            return ResponseEntity.created(new URI("/categories/" + newCategory.getId()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(newCategory);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                    .body("{\"message\":\"There was an error creating the category\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(
            @PathVariable @Min(1) int id, @RequestBody @Valid Category cat
    ) {
        cat.setId(id);
        try {
            return ResponseEntity.ok(cs.updateCategory(cat));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError()
                    .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                    .body("{\"message\":\"There was an error updating the category\"}");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable @Min(1) int id) {
        try {
            cs.deleteCategoryById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                    .body("{\"message\":\"There was an error deleting the category\"}");
        }
    }
}
