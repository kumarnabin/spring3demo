package com.boilerplate.demo.service;

import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.entity.Skill;
import com.boilerplate.demo.entity.Training;
import com.boilerplate.demo.mapper.IdEntityMapper;
import com.boilerplate.demo.repository.PersonRepository;
import com.boilerplate.demo.request.PersonRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final IdEntityMapper idEntityMapper;
    private final TrainingService trainingService;
    private final ModelMapper modelMapper;

    public PersonService(PersonRepository personRepository, IdEntityMapper idEntityMapper, TrainingService trainingService, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.idEntityMapper = idEntityMapper;
        this.trainingService = trainingService;
        this.modelMapper = modelMapper;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(PersonRequest personRequest) {
        Person person = modelMapper.map(personRequest, Person.class);
        List<Skill> skills = idEntityMapper.toEntityList(personRequest.getSkills(), Skill.class);
        person.setSkills(skills);
        person = personRepository.save(person);
        List<Training> trainings = new ArrayList<>();
        for (String name : personRequest.getTrainings()) {
            trainings.add(trainingService.findOrCreateTraining(person, name));
        }
        person.setTrainings(trainings);
        return person;
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person update(Long id, PersonRequest personRequest) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if (existingPerson != null) {
            existingPerson.setName(personRequest.getName());
            return personRepository.save(existingPerson);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
