package com.delivery.service.mapper;

import com.delivery.user.domain.DeliveryOrder;
import com.delivery.dto.DeliveryOrderCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeliveryOrderCreateMapper {

    DeliveryOrderCreateMapper INSTANCE = Mappers.getMapper(DeliveryOrderCreateMapper.class);

    DeliveryOrderCreateDto toDto(DeliveryOrder deliveryOrder);

    DeliveryOrder toDbo(DeliveryOrderCreateDto deliveryOrderCreateDto);
}