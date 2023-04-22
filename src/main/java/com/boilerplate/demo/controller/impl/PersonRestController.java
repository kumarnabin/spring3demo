package com.boilerplate.demo.controller.impl;

import com.boilerplate.demo.controller.PersonController;
import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.request.PersonRequest;
import com.boilerplate.demo.response.ApiResponse;
import com.boilerplate.demo.response.PersonResponse;
import com.boilerplate.demo.service.PersonService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web-api/")
public class PersonRestController implements PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public PersonRestController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/persons")
    @Override
    public ResponseEntity<List<PersonResponse>> index() {
        List<PersonResponse> personResponses = personService.findAll()
                .stream()
                .map(person -> modelMapper.map(person, PersonResponse.class)).toList();
        return ResponseEntity.ok(personResponses);
    }

    @PostMapping("/persons")
    @Override
    public ResponseEntity<Object> store(@Valid @RequestBody PersonRequest personRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // validation failed, return an error response
            List<FieldError> errors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors), HttpStatus.BAD_REQUEST);
        }
        Person person = personService.save(personRequest);

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Person Created", modelMapper.map(person, PersonResponse.class)), HttpStatus.CREATED);

    }

    @Override
    @GetMapping("/persons/{id}")
    public ResponseEntity<ApiResponse<PersonResponse>> show(@PathVariable Long id) {
        Person person = personService.findById(id);
        if (person == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Person not found", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Person found", modelMapper.map(person, PersonResponse.class)), HttpStatus.OK);
    }
}
