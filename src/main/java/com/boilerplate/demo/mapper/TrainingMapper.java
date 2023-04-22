package com.boilerplate.demo.mapper;

import com.boilerplate.demo.entity.Training;
import com.boilerplate.demo.request.TrainingRequest;
import com.boilerplate.demo.response.TrainingResponse;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public static TrainingResponse toTrainingResponse(Training training) {
        TrainingResponse response = new TrainingResponse();
        response.setId(training.getId());
        response.setName(training.getName());
        // set other fields as needed
        return response;
    }

    public static Training toTraining(TrainingRequest request) {
        Training training = new Training();
        training.setName(request.getName());
        // set other fields as needed
        return training;
    }

}
