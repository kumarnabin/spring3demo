package com.boilerplate.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SkillResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

}
