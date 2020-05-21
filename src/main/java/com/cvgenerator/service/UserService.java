package com.cvgenerator.service;


import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.entity.UserCv;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    User findUserById(Long id);

    Optional<List<UserCv>> getListOfUserCv(Long id);
}
