package com.delivery.user.service.impl;

import com.delivery.user.domain.User;
import com.delivery.user.dto.signup.SignUpRequest;
import com.delivery.user.exception.UserNotFoundException;
import com.delivery.user.repository.UserRepository;
import com.delivery.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public void createUser(SignUpRequest request) {
        User user = findByUsername(request.getUsername());
    }
}
