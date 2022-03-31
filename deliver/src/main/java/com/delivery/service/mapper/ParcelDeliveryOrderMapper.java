package com.delivery.service.mapper;

import com.delivery.domain.ParcelDeliveryOrder;
import com.delivery.dto.ParcelDeliveryOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParcelDeliveryOrderMapper {

    ParcelDeliveryOrderMapper INSTANCE = Mappers.getMapper(ParcelDeliveryOrderMapper.class);

    ParcelDeliveryOrderDto toDto(ParcelDeliveryOrder parcelOrder);

    ParcelDeliveryOrder toDbo(ParcelDeliveryOrderDto parcelOrderDto);
}