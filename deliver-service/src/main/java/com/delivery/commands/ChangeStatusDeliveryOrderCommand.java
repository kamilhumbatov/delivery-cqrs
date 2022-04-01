package com.delivery.commands;

import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.user.domain.DeliveryOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeStatusDeliveryOrderCommand {

    private DeliveryOrder order;
    private DeliveryOrderStatus status;
}
