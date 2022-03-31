package com.delivery.service;

import com.delivery.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

    RoleDto save(RoleDto role);
}
