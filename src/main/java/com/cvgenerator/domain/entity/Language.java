package com.cvgenerator.domain.entity;

import com.cvgenerator.domain.enums.LanguageLevel;
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

    @Enumerated(EnumType.STRING)
    private LanguageLevel level;
    private Integer stars;

    @ManyToOne
    @JoinColumn(name = "userCvId")
    private UserCv userCv;


}
