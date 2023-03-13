/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.test.firstSpringApp.Services;

import com.test.firstSpringApp.Entities.BookAuthor;
import com.test.firstSpringApp.Entities.BookAuthorPK;
import com.test.firstSpringApp.Repositories.BookAuthorRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author papar
 */
@Service
public class BookAuthorService implements IBookAuthorService{
    
    private BookAuthorRepository baRepository;
    
    public BookAuthorService(BookAuthorRepository baRepository){
        this.baRepository=baRepository;
    }

    @Override
    public Optional<BookAuthor> addAuthorToBook(BookAuthor ba) {
        BookAuthor res;
        try {
            res=baRepository.save(ba);
        } catch (Exception e) {
            res=null;
        }
        return Optional.ofNullable(res);
    }

    @Override
    public void removeAuthorToBook(BookAuthorPK baId) {
        baRepository.deleteById(baId);
    }

    @Override
    public Optional<BookAuthor> addAuthorToBook(int bookId, int authorId) {
        BookAuthor ba=new BookAuthor();
        ba.setId(bookId, authorId);
        BookAuthor res;
        try {
            res=baRepository.save(ba);
        } catch (Exception e) {
            res=null;
        }
        return Optional.ofNullable(res);    }

    @Override
    public void removeAuthorToBook(int bookId, int authorId) {
        BookAuthorPK baPk=new BookAuthorPK();
        baPk.setIdAuthor(authorId);
        baPk.setIdBook(bookId);
        baRepository.deleteById(baPk);
    }
    
}
