package com.cvgenerator.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "usersCv")
public class UserCv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String templateName;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "clauseId")
    private Clause clause;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "summaryId")
    private Summary summary;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "personalDataId")
    private PersonalData personalData;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    private List<Project> projects;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    private List<Skill> skills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    private List<Language> languages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    private List<Interest> interests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    private List<Education> educations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    private List<Experience> experiences;

}
