package com.delivery.user.service.impl;

import com.delivery.domain.Role;
import com.delivery.repository.RoleRepository;
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
