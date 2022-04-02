package com.delivery.user.service.mapper;

import com.delivery.user.domain.Role;
import com.delivery.user.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto toDto(Role role);

    Role toDbo(RoleDto roleDto);
}