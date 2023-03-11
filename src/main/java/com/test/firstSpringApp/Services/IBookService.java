/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.test.firstSpringApp.Services;

import com.test.firstSpringApp.Entities.Book;
import com.test.firstSpringApp.Entities.Category;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author papar
 */
public interface IBookService {
    public List<Book> getAllBooks();
    public Optional<Book> getBookById(int id);
    public Book createBook(Book b);
    public Book updateBook(Book b);
    public void deleteBookById(int id);
    public List<Book> getBooksByCategory(Category cat);
    public List<Book> getBooksByKeyword(String keyword);
}
