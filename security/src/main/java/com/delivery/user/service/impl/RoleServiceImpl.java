package com.delivery.user.service.impl;

import com.delivery.user.dto.RoleDto;
import com.delivery.repository.RoleRepository;
import com.delivery.user.service.RoleService;
import com.delivery.user.service.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public List<RoleDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(RoleMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto save(RoleDto role) {
        return null;
    }
}
