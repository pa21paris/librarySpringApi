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
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Category> getAllCategories(@RequestParam Optional<Integer> id){
        List<Category> res;
        if(!id.isEmpty()){
            res=new ArrayList<>();
            try {
                res.add(cs.getCategoryById(id.get()));
            } catch (Exception e) {
            }
            
        }else{
            res=cs.getAllCategories();
        }
        return res;
    }
}
