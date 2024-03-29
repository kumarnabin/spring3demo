package com.boilerplate.demo.controller.impl;

import com.boilerplate.demo.controller.PersonController;
import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.request.PersonRequest;
import com.boilerplate.demo.response.PersonResponse;
import com.boilerplate.demo.service.PersonService;
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
@RequestMapping("/web-api/persons")
public class PersonRestController implements PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public PersonRestController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<PersonResponse>> index() {
        List<PersonResponse> personResponses = personService.findAll()
                .stream()
                .map(person -> modelMapper.map(person, PersonResponse.class)).toList();
        return ResponseEntity.ok(personResponses);
    }

    @Override
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> store(@Valid @RequestBody PersonRequest personRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createErrorResponse(bindingResult.getFieldErrors());
        }
        Person person = personService.save(personRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(person, PersonResponse.class));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> show(@PathVariable Long id) {
        Person person = personService.findById(id);
        if (person != null) {
            return ResponseEntity.ok(modelMapper.map(person, PersonResponse.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PersonRequest personRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createErrorResponse(bindingResult.getFieldErrors());
        }
        Person updatedPerson = personService.update(id, personRequest);
        if (updatedPerson != null) {
            return ResponseEntity.ok(modelMapper.map(updatedPerson, PersonResponse.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Object> createErrorResponse(List<FieldError> fieldErrors) {
        List<String> errors = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
