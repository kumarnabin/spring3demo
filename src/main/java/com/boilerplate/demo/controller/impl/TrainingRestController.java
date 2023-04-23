package com.boilerplate.demo.controller.impl;

import com.boilerplate.demo.controller.TrainingController;
import com.boilerplate.demo.entity.Training;
import com.boilerplate.demo.request.TrainingRequest;
import com.boilerplate.demo.response.TrainingResponse;
import com.boilerplate.demo.service.TrainingService;
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
@RequestMapping("/web-api/trainings")
public class TrainingRestController implements TrainingController {
    private final TrainingService trainingService;
    private final ModelMapper modelMapper;

    public TrainingRestController(TrainingService trainingService, ModelMapper modelMapper) {
        this.trainingService = trainingService;
        this.modelMapper = modelMapper;
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<TrainingResponse>> index() {
        List<TrainingResponse> trainingResponses = trainingService.findAll()
                .stream()
                .map(training -> modelMapper.map(training, TrainingResponse.class)).toList();
        return ResponseEntity.ok(trainingResponses);
    }

    @Override
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> store(@Valid @RequestBody TrainingRequest trainingRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createErrorResponse(bindingResult.getFieldErrors());
        }
        Training training = trainingService.save(trainingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(training, TrainingResponse.class));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TrainingResponse> show(@PathVariable Long id) {
        Training training = trainingService.findById(id);
        if (training != null) {
            return ResponseEntity.ok(modelMapper.map(training, TrainingResponse.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody TrainingRequest trainingRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createErrorResponse(bindingResult.getFieldErrors());
        }
        Training updatedTraining = trainingService.update(id, trainingRequest);
        if (updatedTraining != null) {
            return ResponseEntity.ok(modelMapper.map(updatedTraining, TrainingResponse.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trainingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Object> createErrorResponse(List<FieldError> fieldErrors) {
        List<String> errors = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
