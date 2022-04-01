package com.delivery.user.service;

import com.delivery.user.domain.User;
import com.delivery.user.dto.signup.SignUpRequest;

public interface UserService {

    User findByUsername(String username);

    void createUser(SignUpRequest request);
}
