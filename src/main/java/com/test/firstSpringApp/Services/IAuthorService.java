/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.test.firstSpringApp.Services;

import com.test.firstSpringApp.Entities.Author;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author papar
 */
public interface IAuthorService {
    public List<Author> getAllAuthors();
    public Optional<Author> getAuthorById(int id);
    public Optional<Author> createAuthor(Author a);
    public Optional<Author> updateAuthor(Author a);
    public void deleteAuthor(int id);
    public List<Author> getAuthorsByKeyWord(String keyword);
}
