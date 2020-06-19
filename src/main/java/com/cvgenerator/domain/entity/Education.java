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
@Entity
public class Education {

    /**
     *  Annotation @JsonSerialize(using = LocalDateSerializer.class) is required for
     *  correct display LocalDate in tests
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String school;
    private String city;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate finishDate;
    private String degree;
    private String specialization;
    private String description;

    @ManyToOne
    @JoinColumn(name = "userCvId")
    private UserCv userCv;

}
