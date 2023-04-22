package com.boilerplate.demo.controller;

import com.boilerplate.demo.request.PersonRequest;
import com.boilerplate.demo.response.ApiResponse;
import com.boilerplate.demo.response.PersonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface PersonController {
    ResponseEntity<List<PersonResponse>> index();

    ResponseEntity store(PersonRequest personRequest, BindingResult bindingResult);

    ResponseEntity<ApiResponse<PersonResponse>> show(Long id);
}
