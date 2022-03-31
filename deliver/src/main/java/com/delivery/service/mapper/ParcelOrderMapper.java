package com.delivery.service.mapper;

import com.delivery.domain.ParcelOrder;
import com.delivery.dto.ParcelOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParcelOrderMapper {

    ParcelOrderMapper INSTANCE = Mappers.getMapper(ParcelOrderMapper.class);

    ParcelOrderDto toDto(ParcelOrder parcelOrder);

    ParcelOrder toDbo(ParcelOrderDto parcelOrderDto);
}