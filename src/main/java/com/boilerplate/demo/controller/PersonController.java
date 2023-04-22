package com.boilerplate.demo.controller;

import com.boilerplate.demo.request.PersonRequest;
import com.boilerplate.demo.response.PersonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonController {
    ResponseEntity<List<PersonResponse>> index();

    ResponseEntity<PersonResponse> store(PersonRequest personRequest);

}
