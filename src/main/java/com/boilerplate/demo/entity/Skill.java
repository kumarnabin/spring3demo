package com.boilerplate.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public class Skill {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    private List<Person> persons;

    public Skill() {
    }

    public Skill(Long id, String name, List<Person> persons) {
        this.id = id;
        this.name = name;
        this.persons = persons;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
