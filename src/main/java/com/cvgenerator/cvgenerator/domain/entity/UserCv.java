package com.cvgenerator.cvgenerator.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String summary;
    private String clause;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "personalDataId")
    private PersonalData personalData;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    List<Project> projects;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    List<Skill> skills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    List<Language> languages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    List<Interest> interests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    List<Education> educations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    List<Experience> experiences;

}
