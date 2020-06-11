package com.cvgenerator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCvDto {

    private Long id;
    private String name;
    private String templateName;
    private String summary;
    private String clause;
    private PersonalDataDto personalData;
    private List<CourseDto> courses;
    private List<ProjectDto> projects;
    private List<SkillDto> skills;
    private List<LanguageDto> languages;
    private List<InterestDto> interests;
    private List<EducationDto> educations;
    private List<ExperienceDto> experiences;

}
