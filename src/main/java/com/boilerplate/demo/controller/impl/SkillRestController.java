package com.boilerplate.demo.controller.impl;

import com.boilerplate.demo.controller.SkillController;
import com.boilerplate.demo.entity.Skill;
import com.boilerplate.demo.request.SkillRequest;
import com.boilerplate.demo.response.SkillResponse;
import com.boilerplate.demo.service.SkillService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web-api/skills")
public class SkillRestController implements SkillController {
    private final SkillService skillService;
    private final ModelMapper modelMapper;

    public SkillRestController(SkillService skillService, ModelMapper modelMapper) {
        this.skillService = skillService;
        this.modelMapper = modelMapper;
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<SkillResponse>> index() {
        List<SkillResponse> skillResponses = skillService.findAll()
                .stream()
                .map(skill -> modelMapper.map(skill, SkillResponse.class)).toList();
        return ResponseEntity.ok(skillResponses);
    }

    @Override
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> store(@Valid @RequestBody SkillRequest skillRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createErrorResponse(bindingResult.getFieldErrors());
        }
        Skill skill = skillService.save(skillRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(skill, SkillResponse.class));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SkillResponse> show(@PathVariable Long id) {
        Skill skill = skillService.findById(id);
        if (skill != null) {
            return ResponseEntity.ok(modelMapper.map(skill, SkillResponse.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody SkillRequest skillRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createErrorResponse(bindingResult.getFieldErrors());
        }
        Skill updatedSkill = skillService.update(id, skillRequest);
        if (updatedSkill != null) {
            return ResponseEntity.ok(modelMapper.map(updatedSkill, SkillResponse.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Object> createErrorResponse(List<FieldError> fieldErrors) {
        List<String> errors = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
