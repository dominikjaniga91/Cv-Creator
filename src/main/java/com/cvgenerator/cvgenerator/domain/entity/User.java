package com.cvgenerator.cvgenerator.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive;
    private LocalDateTime registrationDate;

    @ManyToMany
    @JoinColumn(name = "userCvId")
    UserCv userCv;


}
