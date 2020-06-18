package com.cvgenerator.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String profession;
    private String linkedIn;
    private String github;
    private String twitter;
    private String website;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToOne(mappedBy = "personalData")
    private UserCv userCv;

}
