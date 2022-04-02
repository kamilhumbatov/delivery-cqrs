package com.delivery.user.service;

import com.delivery.user.domain.User;
import com.delivery.user.dto.signup.SignUpRequest;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    String createCustomer(SignUpRequest request);
}
