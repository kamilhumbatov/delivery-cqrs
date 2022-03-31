package com.delivery.service.impl;

import com.delivery.client.UserClient;
import com.delivery.dto.UserCreateDto;
import com.delivery.service.UserService;
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
