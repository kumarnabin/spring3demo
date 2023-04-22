package com.boilerplate.demo.service;

import com.boilerplate.demo.entity.Training;
import com.boilerplate.demo.mapper.TrainingMapper;
import com.boilerplate.demo.repository.TrainingRepository;
import com.boilerplate.demo.request.TrainingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> findAll() {
        return trainingRepository.findAll();
    }
    public Training save(TrainingRequest trainingRequest) {
        Training training = TrainingMapper.toTraining(trainingRequest);

        return trainingRepository.save(training);
    }
}
