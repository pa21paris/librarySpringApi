/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.test.firstSpringApp.Repositories;

import com.test.firstSpringApp.Entities.Author;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author papar
 */
@Repository
public interface AuthorRepository extends ListCrudRepository<Author, Integer>{
    public List<Author> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);
}
