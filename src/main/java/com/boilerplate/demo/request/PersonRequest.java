package com.boilerplate.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonRequest {
    @JsonProperty("name")
    @NotNull
    @Size(min = 2, max = 14)
    private String name;
    private List<Long> skills;
}
