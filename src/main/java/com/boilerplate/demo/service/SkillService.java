package com.boilerplate.demo.service;

import com.boilerplate.demo.entity.Skill;
import com.boilerplate.demo.repository.SkillRepository;
import com.boilerplate.demo.request.SkillRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    public SkillService(SkillRepository skillRepository, ModelMapper modelMapper) {
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Skill save(SkillRequest skillRequest) {
        Skill skill = modelMapper.map(skillRequest, Skill.class);
        return skillRepository.save(skill);
    }

    public Skill findById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    public Skill update(Long id, SkillRequest skillRequest) {
        Skill existingSkill = skillRepository.findById(id).orElse(null);
        if (existingSkill != null) {
            existingSkill.setName(skillRequest.getName());
            return skillRepository.save(existingSkill);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }
}
