/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.Entities;

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
    private BookAuthorPK id;
    
    @ManyToOne
    @JoinColumn(name = "ID_BOOK",insertable = false,updatable = false)
    private Book book;
    
    @ManyToOne
    @JoinColumn(name = "ID_AUTHOR",updatable = false,insertable = false)
    private Author author;
    
    public BookAuthorPK getId() {
        return id;
    }

    public void setId(BookAuthorPK id) {
        this.id = id;
    }
    
}
