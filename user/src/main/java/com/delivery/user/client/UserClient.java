package com.delivery.user.client;

import com.delivery.user.dto.UserCreateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "UserClient", url = "${security.path}")
public interface UserClient {

    @PostMapping
    void add(UserCreateDto dto);
}
