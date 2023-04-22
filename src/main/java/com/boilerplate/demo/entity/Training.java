package com.boilerplate.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public class Training {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Person person;

    public Training() {
    }

    public Training(Long id, String name, Person person) {
        this.id = id;
        this.name = name;
        this.person = person;
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


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
