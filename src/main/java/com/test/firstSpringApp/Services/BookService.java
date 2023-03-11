/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Services;

import com.test.firstSpringApp.Entities.Book;
import com.test.firstSpringApp.Entities.Category;
import com.test.firstSpringApp.Repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author papar
 */
@Service
public class BookService implements IBookService{

    private BookRepository br;
    
    public BookService(BookRepository br) {
        this.br=br;
    }    

    @Override
    public List<Book> getAllBooks() {
        return br.findAll();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return br.findById(id);
    }

    @Override
    /**
     * Precondition: b.id=0
     */
    public Book createBook(Book b) {
        return br.save(b);
    }

    @Override
    
    /**
     * Precondition: b.id != 0
     */
    public Book updateBook(Book b) {
        return br.save(b);
    }

    @Override
    public void deleteBookById(int id) {
        br.deleteById(id);
    }

    @Override
    public List<Book> getBooksByCategory(Category cat) {
        return br.findByCategoryId(cat.getId());
    }

    @Override
    public List<Book> getBooksByKeyword(String keyword) {
        return br.findByBookTitleLike("%"+keyword.trim()+"%");
    }
    
}
