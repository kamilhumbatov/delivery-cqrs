package com.delivery.user.service;

import com.delivery.user.domain.User;

public interface UserService {

    User findByUsername(String username);
}
