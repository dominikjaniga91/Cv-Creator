package com.cvgenerator.cvgenerator.service;


import com.cvgenerator.cvgenerator.domain.entity.User;

import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    Optional<User> findUserById(Long id);
}
