package com.cvgenerator.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity(name = "projects")
public class Project {

    /**
     *  Annotation @JsonSerialize(using = LocalDateSerializer.class) is required for
     *  correct display LocalDate in tests
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate finishDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "userCvId")
    private UserCv userCv;

}
