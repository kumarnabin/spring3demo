package com.boilerplate.demo.request;

import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.repository.PersonRepository;
import com.boilerplate.demo.validation.ExistsColumn;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequest {
    @JsonProperty("personId")
    @NotNull
    @ExistsColumn(entityClass = Person.class, columnName = "id")
    private Long personId;
    @JsonProperty("name")
    @NotNull
    @Size(min = 2, max = 14)
    private String name;

}
