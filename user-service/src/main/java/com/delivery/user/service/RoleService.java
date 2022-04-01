package com.delivery.user.service;

import com.delivery.user.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

    RoleDto save(RoleDto role);
}
