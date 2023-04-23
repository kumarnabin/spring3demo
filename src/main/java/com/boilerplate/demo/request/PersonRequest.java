package com.boilerplate.demo.request;

import com.boilerplate.demo.entity.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
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
public class PersonRequest {
    @JsonProperty("name")
    @NotNull
    @Size(min = 2, max = 14)
    private String name;
}
