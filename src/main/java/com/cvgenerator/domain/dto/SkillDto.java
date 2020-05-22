package com.cvgenerator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {

    private Long id;
    private String name;
    private String description;
    private String level;
    private Integer stars;

}
