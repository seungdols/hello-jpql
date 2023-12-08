package com.study.jpa.jpql;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@NamedQuery(
        name="Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member() {
    }

    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
