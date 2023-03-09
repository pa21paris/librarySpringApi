/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Services;

import com.test.firstSpringApp.Repositories.CategoryRepository;
import com.test.firstSpringApp.Entities.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author papar
 */
@Service
public class CategoryService implements ICategoryService{
    
    @Autowired
    private CategoryRepository cr;

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>)(cr.findAll());
    }

    @Override
    public Category getCategoryById(int id) {
            return cr.findById(id).get();
    }

    @Override
    public Category getCategoryByName(String name) {
        return cr.findByCategoryDescription(name);
    }

    @Override
    public Category createNewCategory(Category cat) {
        return cr.save(cat);
    }

    @Override
    public Category updateCategory(Category cat) {
        return cr.save(cat);
    }

    @Override
    public void deleteCategoryById(int id) {
        cr.deleteById(id);
    }
        
}
