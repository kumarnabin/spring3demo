package com.boilerplate.demo.entity;

import com.boilerplate.demo.service.PersonService;
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

    @ManyToMany(mappedBy = "persons")
    private List<Skill> skills;


    public Person() {
    }

    public Person(Long id, String name, List<Training> trainings, List<Skill> skills) {
        this.id = id;
        this.name = name;
        this.trainings = trainings;
        this.skills = skills;
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
