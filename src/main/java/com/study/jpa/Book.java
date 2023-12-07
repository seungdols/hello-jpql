package com.study.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
public class Book {
    @Id
    @GeneratedValue
    private Integer id;

    private String isbn;

    private String title;

    @ManyToOne
    private BookStore bookStore;

    public void addBook(Book book) {
        this.bookStore.getBooks().add(book);
    }
}
