package com.study.jpa.jpql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager em = entityManagerFactory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }

            em.flush();
            em.clear();

            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
                                        .setFirstResult(1)
                                        .setMaxResults(10)
                                        .getResultList();

            for (Member findMember : resultList) {
                System.out.println("findMember: " + findMember.getUsername() + " " + findMember.getAge());
            }

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
