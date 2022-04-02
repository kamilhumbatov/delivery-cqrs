package com.delivery.user.service.impl;

import com.delivery.user.domain.Role;
import com.delivery.user.repository.RoleRepository;
import com.delivery.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role findByDescription(String info) {
        return repository.findByDescription(info);
    }
}
