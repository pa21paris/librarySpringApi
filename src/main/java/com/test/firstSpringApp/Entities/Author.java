/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.firstSpringApp.validationGroups.EntitiesWithoutId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import java.util.List;

/**
 *
 * @author papar
 */
@Entity
@Table(name = "Authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Max(value = 0, message = "id shouldn't be on body", groups = EntitiesWithoutId.class)
    @Min(value = 0, message = "id shouldn't be on body", groups = EntitiesWithoutId.class)
    private int id;
    @Column(name = "FIRST_NAME")
    @NotBlank(message = "Is required")
    private String firstName;
    @Column(name = "LAST_NAME")
    @NotBlank(message = "Is required")
    private String lastName;
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    @Null(message = "There shouldn't be books on body")
    List<BookAuthor> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<BookAuthor> getBooks() {
        return books;
    }

    public void setBooks(List<BookAuthor> books) {
        this.books = books;
    }

}
