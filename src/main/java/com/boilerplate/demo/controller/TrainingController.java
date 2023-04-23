package com.boilerplate.demo.controller;

import com.boilerplate.demo.request.TrainingRequest;
import com.boilerplate.demo.response.TrainingResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TrainingController {
    @GetMapping("")
    ResponseEntity<List<TrainingResponse>> index();

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> store(@Valid @RequestBody TrainingRequest trainingRequest, BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<TrainingResponse> show(@PathVariable Long id);

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody TrainingRequest trainingRequest, BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}
