package com.cvgenerator.service;


import com.cvgenerator.domain.dto.UserCvShortDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.entity.UserCv;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(UserDto userDto);

    UserDto findUserById(Long id);

    List<UserCvShortDto> getListOfUserCv(Long id);

    void updateUser(UserDto userDto);

    void deleteUserAccount(Long userId, String password);

    void updateUserPassword(User user, String password);
}
