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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class CategoryController {
    @Autowired
    private CategoryService cs;
    
    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam Optional<String> keyword){
        if(keyword.isEmpty()){
            try {
                return ResponseEntity.ok(cs.getAllCategories());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseEntity.badRequest().build();
            }
            
        }else{
            return ResponseEntity.ok(cs.getCategoryByKeyWord(keyword.get()));
        }
        
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id){
        Category cat;
        try {
            cat=cs.getCategoryById(id);
        } catch (Exception e) {
            cat=new Category();
        }
        return cat;
    }
    
    @PostMapping()
    public Category createCategory(@RequestBody Category newCategory){
        if(newCategory.getId()!=0){
            Category cat=new Category();
            cat.setCategoryDescription("ids generate automatically");
            return cat;
        }else{
            try {
                newCategory.setId(cs.createNewCategory(newCategory).getId());
            } catch (Exception e) {
                newCategory.setCategoryDescription("There was an error creating the category");
            }
            return newCategory;
        }
        
    }
    
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category cat){
        if(id<=0){
            cat.setCategoryDescription("Invalid id");
        }else if(cat.getId()!=0){
            cat.setCategoryDescription("You shouldn't send id field on body");
        }else{
            cat.setId(id);
            try {
                cat=cs.updateCategory(cat);
            } catch (Exception e) {
                cat.setCategoryDescription("There was an error updating the category");
            }
        }
        return cat;
    }
    
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id){
        String res;
        try {
            cs.deleteCategoryById(id);
            res="Deleted category successfully";
        } catch (Exception e) {
            res="There was an error deleting the category";
        }
        return res;
    }
}
