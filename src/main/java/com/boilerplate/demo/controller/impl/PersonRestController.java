package com.boilerplate.demo.controller.impl;

import com.boilerplate.demo.controller.PersonController;
import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.request.PersonRequest;
import com.boilerplate.demo.response.PersonResponse;
import com.boilerplate.demo.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PersonResponse> store(PersonRequest personRequest) {
        Person person = personService.save(personRequest);

        return ResponseEntity.ok(modelMapper.map(person, PersonResponse.class));
    }
}
