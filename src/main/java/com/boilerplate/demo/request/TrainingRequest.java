package com.boilerplate.demo.request;

import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.entity.Training;
import com.boilerplate.demo.repository.PersonRepository;
import com.boilerplate.demo.validation.ExistsColumn;
import com.boilerplate.demo.validation.Unique;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
