package com.study.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class BookStore {

    @Id @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "bookStore")
    private Set<Book> books = new HashSet<Book>();
}
