/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Repositories;

import com.test.firstSpringApp.Entities.BookAuthor;
import com.test.firstSpringApp.Entities.BookAuthorPK;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author papar
 */
@Repository
public interface BookAuthorRepository extends ListCrudRepository<BookAuthor, BookAuthorPK>{
}
