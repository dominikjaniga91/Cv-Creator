package com.cvgenerator.cvgenerator.service;


import com.cvgenerator.cvgenerator.domain.entity.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findUserById(Long id);
}
