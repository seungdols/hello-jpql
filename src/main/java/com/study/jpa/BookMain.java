package com.study.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BookMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager em = entityManagerFactory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            BookStore bookStore = new BookStore();
            bookStore.setName("Seungdols Book store");
            em.persist(bookStore);

            Book book = new Book();
            book.setTitle("JPA in Action");
            book.setBookStore(bookStore);
            book.addBook(book);

            em.persist(book);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("exception: " + e);
        } finally {
            em.close();
        }
        entityManagerFactory.close();
    }
}
