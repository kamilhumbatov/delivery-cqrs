package com.delivery.user.service.impl;

import com.delivery.user.client.UserClient;
import com.delivery.user.dto.UserCreateDto;
import com.delivery.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceServiceImpl implements UserService {

    private final UserClient securityClient;

    @Override
    public void add(UserCreateDto dto) {
        securityClient.add(dto);
    }
}
