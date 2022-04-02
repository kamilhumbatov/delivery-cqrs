package com.delivery.user.service.mapper;

import com.delivery.domain.User;
import com.delivery.user.dto.signup.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    SignUpRequest toDto(User user);

    User toDbo(SignUpRequest roleDto);
}