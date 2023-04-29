package com.boilerplate.demo.repository;

import com.boilerplate.demo.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findAllByIdIn(List<Long> skillIds);
}
