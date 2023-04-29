package com.boilerplate.demo.controller;

import com.boilerplate.demo.request.SkillRequest;
import com.boilerplate.demo.response.SkillResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface SkillController {
    @GetMapping("")
    ResponseEntity<List<SkillResponse>> index();

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> store(@Valid @RequestBody SkillRequest SkillRequest, BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<SkillResponse> show(@PathVariable Long id);

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody SkillRequest SkillRequest, BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}
