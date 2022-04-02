package com.delivery.commands;

import com.delivery.dto.DeliveryOrderDestinationDto;
import com.delivery.user.domain.DeliveryOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeOrderCoordinateCommand {
    private DeliveryOrder order;
    private DeliveryOrderDestinationDto coordinate;
}
