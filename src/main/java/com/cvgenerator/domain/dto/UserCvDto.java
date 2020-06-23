package com.cvgenerator.domain.dto;

import com.cvgenerator.domain.entity.*;
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
    private Summary summary;
    private Clause clause;
    private PersonalDataDto personalData;
    private List<CourseDto> courses;
    private List<ProjectDto> projects;
    private List<SkillDto> skills;
    private List<LanguageDto> languages;
    private List<InterestDto> interests;
    private List<EducationDto> educations;
    private List<ExperienceDto> experiences;

    public UserCvDto(UserCvDtoBuilder builder){
        id              = builder.id;
        name            = builder.name;
        templateName    = builder.templateName;
        summary         = builder.summary;
        clause          = builder.clause;
        personalData    = builder.personalData;
        courses         = builder.courses;
        projects        = builder.projects;
        skills          = builder.skills;
        languages       = builder.languages;
        interests       = builder.interests;
        educations      = builder.educations;
        experiences     = builder.experiences;
    }

    public static class UserCvDtoBuilder{

        private Long id;
        private String name;
        private String templateName;
        private Summary summary;
        private Clause clause;
        private PersonalDataDto personalData;
        private List<CourseDto> courses;
        private List<ProjectDto> projects;
        private List<SkillDto> skills;
        private List<LanguageDto> languages;
        private List<InterestDto> interests;
        private List<EducationDto> educations;
        private List<ExperienceDto> experiences;

        public UserCvDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserCvDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserCvDtoBuilder setTemplateName(String templateName) {
            this.templateName = templateName;
            return this;
        }

        public UserCvDtoBuilder setSummary(Summary summary) {
            this.summary = summary;
            return this;
        }

        public UserCvDtoBuilder setClause(Clause clause) {
            this.clause = clause;
            return this;
        }

        public UserCvDtoBuilder setPersonalData(PersonalDataDto personalData) {
            this.personalData = personalData;
            return this;
        }

        public UserCvDtoBuilder setCourses(List<CourseDto> courses) {
            this.courses = courses;
            return this;
        }

        public UserCvDtoBuilder setProjects(List<ProjectDto> projects) {
            this.projects = projects;
            return this;
        }

        public UserCvDtoBuilder setSkills(List<SkillDto> skills) {
            this.skills = skills;
            return this;
        }

        public UserCvDtoBuilder setLanguages(List<LanguageDto> languages) {
            this.languages = languages;
            return this;
        }

        public UserCvDtoBuilder setInterests(List<InterestDto> interests) {
            this.interests = interests;
            return this;
        }

        public UserCvDtoBuilder setEducations(List<EducationDto> educations) {
            this.educations = educations;
            return this;
        }

        public UserCvDtoBuilder setExperiences(List<ExperienceDto> experiences) {
            this.experiences = experiences;
            return this;
        }

        public UserCvDto buildUserCvDto(){
            return new UserCvDto(this);
        }
    }
}
