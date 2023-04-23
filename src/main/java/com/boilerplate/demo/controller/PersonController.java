package com.boilerplate.demo.controller;

import com.boilerplate.demo.request.PersonRequest;
import com.boilerplate.demo.response.PersonResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PersonController {

    @GetMapping("")
    ResponseEntity<List<PersonResponse>> index();

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> store(@Valid @RequestBody PersonRequest personRequest, BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<PersonResponse> show(@PathVariable Long id);

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PersonRequest personRequest, BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
