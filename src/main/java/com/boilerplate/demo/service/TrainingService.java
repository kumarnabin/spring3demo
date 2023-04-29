package com.boilerplate.demo.service;

import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.entity.Training;
import com.boilerplate.demo.repository.PersonRepository;
import com.boilerplate.demo.repository.TrainingRepository;
import com.boilerplate.demo.request.TrainingRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {
    private final PersonRepository personRepository;
    private final TrainingRepository trainingRepository;
    private final ModelMapper modelMapper;

    public TrainingService(PersonRepository personRepository, TrainingRepository trainingRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.trainingRepository = trainingRepository;
        this.modelMapper = modelMapper;
    }

    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    public Training save(TrainingRequest trainingRequest) {
        Training training = modelMapper.map(trainingRequest, Training.class);
        training.setPerson(personRepository.findById(trainingRequest.getPersonId()).orElse(null));
        return trainingRepository.save(training);
    }

    public Training findById(Long id) {
        return trainingRepository.findById(id).orElse(null);
    }

    public Training update(Long id, TrainingRequest trainingRequest) {
        Training existingTraining = trainingRepository.findById(id).orElse(null);
        if (existingTraining != null) {
            existingTraining.setName(trainingRequest.getName());
            existingTraining.setPerson(personRepository.findById(trainingRequest.getPersonId()).orElse(null));
            return trainingRepository.save(existingTraining);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        trainingRepository.deleteById(id);
    }

    public Training findOrCreateTraining(Person person, String name) {
        Optional<Training> trainingOptional = trainingRepository.findFirstByPersonAndName(person, name.trim());
        Training training;
        if (trainingOptional.isPresent()) {
            return trainingOptional.get();
        } else {
            training = new Training();
            training.setPerson(person);
            training.setName(name.trim());
            return trainingRepository.save(training);
        }
    }
}
