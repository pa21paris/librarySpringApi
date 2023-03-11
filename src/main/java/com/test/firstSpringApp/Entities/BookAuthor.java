/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author papar
 */
@Entity
@Table(name = "Book_Author")
public class BookAuthor {
    @EmbeddedId
    @JsonIgnore
    private BookAuthorPK id;
    
    @ManyToOne
    @JoinColumn(name = "ID_BOOK",insertable = false,updatable = false)
    @JsonIgnoreProperties("authors")
    @JsonUnwrapped
    private Book book;
    
    @ManyToOne
    @JoinColumn(name = "ID_AUTHOR",updatable = false,insertable = false)
    @JsonIgnoreProperties("books")
    @JsonUnwrapped
    private Author author;
    
    public BookAuthorPK getId() {
        return id;
    }

    public void setId(BookAuthorPK id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    
}
