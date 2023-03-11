/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.test.firstSpringApp.Repositories;

import com.test.firstSpringApp.Entities.Book;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author papar
 */
@Repository
public interface BookRepository extends ListCrudRepository<Book, Integer>{
    public List<Book> findByCategoryId(int categoryId);
    public List<Book> findByBookTitleLike(String bookTitle);
}
