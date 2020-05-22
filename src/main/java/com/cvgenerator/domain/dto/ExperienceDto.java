package com.cvgenerator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto {

    private Long id;
    private String company;
    private String city;
    private String position;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String description;

}
