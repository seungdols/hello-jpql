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

            String query = "select t from Team t";
            List<Team> resultList = em.createQuery(query, Team.class)
                                      .setFirstResult(0)
                                      .setMaxResults(2)
                                      .getResultList();

            for (Team team : resultList) {
                System.out.println("team.name = " + team.getName() + ", team=" + team);
                for (Member member : team.getMembers()) {
                    System.out.println("-> username: " + member.getUsername() + ", member=" + member);
                }
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
