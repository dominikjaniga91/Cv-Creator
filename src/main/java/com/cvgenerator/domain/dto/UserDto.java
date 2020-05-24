package com.cvgenerator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String password;
    private boolean isActive;
    private LocalDateTime registration;
    private List<UserCvShortDto> userCvDtoList;
}
