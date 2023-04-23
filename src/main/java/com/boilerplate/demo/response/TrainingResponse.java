package com.boilerplate.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("personId")
    private Long personId;
    @JsonProperty("name")
    private String name;

}
