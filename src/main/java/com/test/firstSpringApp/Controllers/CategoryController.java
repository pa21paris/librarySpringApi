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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author papar
 */
@RestController
public class CategoryController {
    @Autowired
    private CategoryService cs;
    
    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return cs.getAllCategories();
    }
    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable int id){
        Category cat;
        try {
            cat=cs.getCategoryById(id);
        } catch (Exception e) {
            cat=new Category();
        }
        return cat;
    }
    
    @PostMapping("/categories")
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
    
    @PutMapping("/categories/{id}")
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
    
    @DeleteMapping("/categories/{id}")
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
