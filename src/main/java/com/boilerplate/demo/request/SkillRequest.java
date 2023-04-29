package com.boilerplate.demo.request;

import com.boilerplate.demo.entity.Person;
import com.boilerplate.demo.validation.ExistsColumn;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SkillRequest {
    @JsonProperty("name")
    @NotNull
    @Size(min = 2, max = 14)
    private String name;

}
