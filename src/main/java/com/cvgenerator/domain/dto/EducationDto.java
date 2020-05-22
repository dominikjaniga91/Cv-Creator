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
public class EducationDto {

    private Long id;
    private String school;
    private String city;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String degree;
    private String specialization;
    private String description;

}
