package com.boilerplate.demo.controller.impl;

import com.boilerplate.demo.controller.PersonController;
import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.mapper.PersonMapper;
import com.boilerplate.demo.request.PersonRequest;
import com.boilerplate.demo.response.PersonResponse;
import com.boilerplate.demo.service.PersonService;
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

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    @Override
    public ResponseEntity<List<PersonResponse>> index() {
        List<PersonResponse> personResponses = personService.findAll()
                .stream()
                .map(PersonMapper::toPersonResponse).toList();
        return ResponseEntity.ok(personResponses);
    }

    @PostMapping("/persons")
    @Override
    public ResponseEntity<PersonResponse> store(PersonRequest personRequest) {
        Person person = personService.save(personRequest);

        return ResponseEntity.ok(PersonMapper.toPersonResponse(person));
    }
}
