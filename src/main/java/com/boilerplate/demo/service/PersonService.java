package com.boilerplate.demo.service;

import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.mapper.PersonMapper;
import com.boilerplate.demo.repository.PersonRepository;
import com.boilerplate.demo.request.PersonRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
    public Person save(PersonRequest personRequest) {
        Person person = PersonMapper.toPerson(personRequest);

        return personRepository.save(person);
    }
}
