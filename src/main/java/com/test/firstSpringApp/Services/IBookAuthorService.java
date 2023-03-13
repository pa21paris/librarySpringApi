/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.test.firstSpringApp.Services;

import com.test.firstSpringApp.Entities.BookAuthor;
import com.test.firstSpringApp.Entities.BookAuthorPK;
import java.util.Optional;

/**
 *
 * @author papar
 */
public interface IBookAuthorService {
    public Optional<BookAuthor> addAuthorToBook(BookAuthor ba);
    public Optional<BookAuthor> addAuthorToBook(int bookId, int authorId);
    public void removeAuthorToBook(BookAuthorPK baId);
    public void removeAuthorToBook(int bookId, int authorId);
    
}
