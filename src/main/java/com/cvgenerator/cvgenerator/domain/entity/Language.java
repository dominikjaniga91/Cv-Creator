package com.cvgenerator.cvgenerator.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String level;
    private Integer start;

    @ManyToOne
    @JoinColumn(name = "userCvId")
    private UserCv userCv;


}
