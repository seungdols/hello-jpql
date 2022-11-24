package com.study.jpa.jpql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager em = entityManagerFactory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("seungdols");
            em.persist(member);

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
