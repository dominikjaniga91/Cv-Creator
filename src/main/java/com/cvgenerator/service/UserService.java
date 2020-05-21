package com.cvgenerator.service;


import com.cvgenerator.domain.entity.User;

import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    User findUserById(Long id);
}
