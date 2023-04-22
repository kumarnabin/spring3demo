package com.boilerplate.demo.request;

import com.boilerplate.demo.entity.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequest {
    @JsonProperty("name")
    private String name;

    public TrainingRequest(Person person) {
        name = person.getName();
    }
}
