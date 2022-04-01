package com.delivery.user.service.mapper;

import com.delivery.user.domain.User;
import com.delivery.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    User toDbo(UserDto roleDto);
}