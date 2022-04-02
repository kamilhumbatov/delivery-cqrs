package com.delivery.user.service;

import com.delivery.user.domain.Role;

public interface RoleService {

    Role findByDescription(String info);
}
