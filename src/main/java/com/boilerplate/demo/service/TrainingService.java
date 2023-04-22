package com.boilerplate.demo.service;

import com.boilerplate.demo.entity.Training;
import com.boilerplate.demo.repository.TrainingRepository;
import com.boilerplate.demo.request.TrainingRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final ModelMapper modelMapper;

    public TrainingService(TrainingRepository trainingRepository, ModelMapper modelMapper) {
        this.trainingRepository = trainingRepository;
        this.modelMapper = modelMapper;
    }

    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    public Training save(TrainingRequest trainingRequest) {
        Training training = modelMapper.map(trainingRequest, Training.class);

        return trainingRepository.save(training);
    }
}
