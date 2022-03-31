package com.delivery.client;

import com.delivery.dto.UserCreateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "UserClient", url = "${security.path}")
public interface UserClient {

    @PostMapping
    void add(UserCreateDto dto);
}
