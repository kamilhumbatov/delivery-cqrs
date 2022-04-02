package com.delivery.deliver.service.mapper;

import com.delivery.deliver.domain.DeliveryOrderDestination;
import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeliveryOrderDestinationCreateMapper {

    DeliveryOrderDestinationCreateMapper INSTANCE =
            Mappers.getMapper(DeliveryOrderDestinationCreateMapper.class);

    DeliveryOrderDestination toDbo(DeliveryOrderCreateDto orderCreateDto);
}