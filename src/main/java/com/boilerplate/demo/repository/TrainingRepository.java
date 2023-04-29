package com.boilerplate.demo.repository;

import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    Optional<Training> findFirstByPersonAndName(Person person, String name);
}
