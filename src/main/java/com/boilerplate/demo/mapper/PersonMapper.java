package com.boilerplate.demo.mapper;

import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.request.PersonRequest;
import com.boilerplate.demo.response.PersonResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public static PersonResponse toPersonResponse(Person person) {
        PersonResponse response = new PersonResponse();
        response.setId(person.getId());
        response.setName(person.getName());
        // set other fields as needed
        return response;
    }

    public static Person toPerson(PersonRequest request) {
        Person person = new Person();
        person.setName(request.getName());
        // set other fields as needed
        return person;
    }

}
