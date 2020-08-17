package com.cvgenerator.domain.dto;

import com.cvgenerator.domain.enums.Country;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean enable2FA;
    private boolean isActive;
    private String phoneNumber;
    private Country country;
    private LocalDateTime registration;
    private List<UserCvShortDto> userCvDtoList;

    private UserDto(UserDtoBuilder builder){
        id              = builder.id;
        firstName       = builder.firstName;
        lastName        = builder.lastName;
        email           = builder.email;
        role            = builder.role;
        password        = builder.password;
        enable2FA       = builder.enable2FA;
        isActive        = builder.isActive;
        phoneNumber     = builder.phoneNumber;
        country         = builder.country;
        registration    = builder.registration;
        userCvDtoList   = builder.userCvDtoList;
    }

    public static class UserDtoBuilder{

        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String role;
        private String password;
        private boolean enable2FA;
        private boolean isActive;
        private String phoneNumber;
        private Country country;
        private LocalDateTime registration;
        private List<UserCvShortDto> userCvDtoList;

        public UserDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDtoBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDtoBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public UserDtoBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDtoBuilder setEnable2FA(boolean enable2FA) {
            this.enable2FA = enable2FA;
            return this;
        }

        public UserDtoBuilder setActive(boolean active) {
            isActive = active;
            return this;
        }

        public UserDtoBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserDtoBuilder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public UserDtoBuilder setRegistration(LocalDateTime registration) {
            this.registration = registration;
            return this;
        }

        public UserDtoBuilder setUserCvDtoList(List<UserCvShortDto> userCvDtoList) {
            this.userCvDtoList = userCvDtoList;
            return this;
        }

        public UserDto buildUserDto(){
            return new UserDto(this);
        }

    }
}
