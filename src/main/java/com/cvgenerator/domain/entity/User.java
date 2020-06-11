package com.cvgenerator.domain.entity;

import com.cvgenerator.domain.dto.UserCvShortDto;
import com.cvgenerator.domain.dto.UserDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String password;
    private boolean isActive;
    private LocalDateTime registration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<UserCv> listOfUserCv = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Token> tokens;

    private User(UserBuilder builder){
        id              = builder.id;
        firstName       = builder.firstName;
        lastName        = builder.lastName;
        email           = builder.email;
        role            = builder.role;
        password        = builder.password;
        isActive        = builder.isActive;
        registration    = builder.registration;
        listOfUserCv    = builder.listOfUserCv;
        tokens          = builder.tokens;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static class UserBuilder{

        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String role;
        private String password;
        private boolean isActive;
        private LocalDateTime registration;
        private List<UserCv> listOfUserCv;
        private List<Token> tokens;

        public UserBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public  UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setActive(boolean active) {
            isActive = active;
            return this;
        }

        public UserBuilder setRegistration(LocalDateTime registration) {
            this.registration = registration;
            return this;
        }

        public UserBuilder setUserCvList(List<UserCv> listOfUserCv) {
            this.listOfUserCv = listOfUserCv;
            return this;
        }

        public UserBuilder setUserTokenList(List<Token> tokens) {
            this.tokens = tokens;
            return this;
        }

        public User buildUserDto(){
            return new User(this);
        }

    }
}
