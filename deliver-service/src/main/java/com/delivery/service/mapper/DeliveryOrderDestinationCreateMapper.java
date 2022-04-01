package com.delivery.service.mapper;

import com.delivery.user.domain.DeliveryOrderDestination;
import com.delivery.dto.DeliveryOrderCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeliveryOrderDestinationCreateMapper {

    DeliveryOrderDestinationCreateMapper INSTANCE =
            Mappers.getMapper(DeliveryOrderDestinationCreateMapper.class);

    DeliveryOrderDestination toDbo(DeliveryOrderCreateDto orderCreateDto);
}