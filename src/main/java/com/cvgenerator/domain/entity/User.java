package com.cvgenerator.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
    private LocalDateTime registration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<UserCv> listOfUserCv = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Token> tokens;
}
