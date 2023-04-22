package com.boilerplate.demo.controller;

import com.boilerplate.demo.request.TrainingRequest;
import com.boilerplate.demo.response.TrainingResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrainingController {
    ResponseEntity<List<TrainingResponse>> index();

    ResponseEntity<TrainingResponse> store(TrainingRequest TrainingRequest);

}
