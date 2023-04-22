package com.boilerplate.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Entity
@Builder
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;


    @OneToMany(mappedBy = "person")
    private List<Training> trainings;


    public Person() {
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(Long id, String name, List<Training> trainings) {
        this.id = id;
        this.name = name;
        this.trainings = trainings;
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

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
}
