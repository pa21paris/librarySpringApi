/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.firstSpringApp.Entities.Author;
import com.test.firstSpringApp.Repositories.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author papar
 */
@Service
public class AuthorService implements IAuthorService{
    
    private AuthorRepository ar;

    public AuthorService(AuthorRepository ar){
        this.ar=ar;
    }
    
    @Override
    public List<Author> getAllAuthors() {
        return ar.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        return ar.findById(id);
    }

    @Override
    public Optional<Author> createAuthor(Author a) {
        Author res;
        try {
            res=ar.save(a);
        } catch (Exception e) {
            res=null;
        }
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<Author> updateAuthor(Author a) {
        Author res;
        try {
            res=ar.save(a);
        } catch (Exception e) {
            res=null;
        }
        return Optional.ofNullable(res);
    }

    @Override
    public void deleteAuthor(int id) {
        ar.deleteById(id);
    }

    @Override
    public List<Author> getAuthorsByKeyWord(String keyword) {
        return ar.findByFirstNameLikeOrLastNameLike("%"+keyword+"%", "%"+keyword+"%");
    }
    
}
