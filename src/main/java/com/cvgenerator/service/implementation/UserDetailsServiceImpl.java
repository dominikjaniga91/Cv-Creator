package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.exceptions.notfound.UserNotFoundException;
import com.cvgenerator.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final Messages messages;

    public UserDetailsServiceImpl(UserRepository userRepository,
                                  Messages messages) {
        this.userRepository = userRepository;
        this.messages = messages;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow(() -> new UserNotFoundException(messages.get("user.notfound")));
    }
}
