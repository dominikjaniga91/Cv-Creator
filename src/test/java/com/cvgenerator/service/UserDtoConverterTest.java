package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.service.dtoConverters.UserDtoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserDtoConverterTest {

    @Autowired private UserDtoConverter converter;

    @Test
    @DisplayName("Should return user name after convert to user entity")
    void shouldReturnUserName_afterConvertToUserEntity() {

        UserDto userDto = new UserDto.UserDtoBuilder()
                .setId(1L)
                .setFirstName("Dominik")
                .buildUserDto();

        User user = converter.convertToEntity(userDto);

        Assertions.assertEquals("Dominik", user.getFirstName());
    }

    @Test
    @DisplayName("Should return userDto name after convert to Dto")
    void shouldReturnUserDtoName_afterConvertToDto() {

        User user = new User.UserBuilder()
                .setId(1L)
                .setFirstName("Dominik")
                .buildUserDto();

        UserDto userDto = converter.convertToDto(user);

        Assertions.assertEquals("Dominik", userDto.getFirstName());
    }
}
