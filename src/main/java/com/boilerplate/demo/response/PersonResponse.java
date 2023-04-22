package com.boilerplate.demo.response;

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
public class PersonResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;

}
