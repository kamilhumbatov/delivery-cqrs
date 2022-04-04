package com.delivery.deliver.service.mapper;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeliveryOrderMapper {

    DeliveryOrderMapper INSTANCE = Mappers.getMapper(DeliveryOrderMapper.class);

    DeliveryOrderDto toDto(DeliveryOrder deliveryOrder);

    DeliveryOrder toDbo(DeliveryOrderDto deliveryOrderDto);
}