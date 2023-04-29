package com.boilerplate.demo.response;

import com.boilerplate.demo.entity.Skill;
import com.boilerplate.demo.entity.Training;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("trainings")
    private List<TrainingResponse> trainings;
    @JsonProperty("skills")
    private List<SkillResponse> skills;

}
