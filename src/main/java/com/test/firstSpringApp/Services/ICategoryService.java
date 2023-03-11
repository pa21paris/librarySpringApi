/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.test.firstSpringApp.Services;

import com.test.firstSpringApp.Entities.Category;
import java.util.List;

/**
 *
 * @author papar
 */
public interface ICategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(int id);
    public List<Category> getCategoryByKeyWord(String name);
    public Category createNewCategory(Category cat);
    public Category updateCategory(Category cat);
    public void deleteCategoryById(int id);
    public boolean existCategoryById(int id);
}
