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

            Team teamA = new Team();
            teamA.setName("TeamA");

            Team teamB = new Team();
            teamB.setName("TeamB");

            em.persist(teamA);
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setAge(10);
            member1.setTeam(teamA);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(10);
            member2.setTeam(teamA);
            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setAge(10);
            member3.setTeam(teamB);

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query = "select m from Member m where m = :member";
//            Member findMember = em.createQuery(query, Member.class)
//                                        .setParameter("member", member1)
//                                        .getSingleResult();
//            System.out.println("findMember: " + findMember.getUsername());
            String query = "select m from Member m where m.team = :team";
            List<Member> result = em.createQuery(query, Member.class)
                                    .setParameter("team", teamA)
                                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member);
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
