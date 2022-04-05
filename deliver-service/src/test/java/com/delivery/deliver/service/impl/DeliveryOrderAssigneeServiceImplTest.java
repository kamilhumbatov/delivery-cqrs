package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderStatusException;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.commands.OrderCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryOrderAssigneeServiceImplTest {

    private static final String ID = "121212";
    private static final String ASSIGNEE = "courier";

    private static final String RESULT = "12-12";
    private DeliveryOrder deliveryOrderStatusPending;
    private DeliveryOrder deliveryOrderStatusDelivery;

    @InjectMocks
    DeliveryOrderAssigneeServiceImpl deliveryOrderAssigneeService;

    @Mock
    DeliveryOrderService orderService;

    @Mock
    OrderCommandService orderCommand;

    @BeforeEach
    void setup() {
        deliveryOrderStatusPending = DeliveryOrder.builder()
                .id(ID)
                .status(DeliveryOrderStatus.PENDING)
                .build();
        deliveryOrderStatusDelivery = DeliveryOrder.builder()
                .id(ID)
                .status(DeliveryOrderStatus.DELIVERY)
                .build();
    }

    @Test
    void givenOrderWithPendingStatusAndDestinationWhenChangeDestinationThenOk() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusPending);
        when(orderCommand.assigneeOrder(ID, ASSIGNEE)).thenReturn(RESULT);

        //act
        String response = deliveryOrderAssigneeService.assigneeOrderToCourier(ID, ASSIGNEE);

        //assert
        assertThat(response).isEqualTo(RESULT);
        verify(orderCommand, times(1)).assigneeOrder(ID, ASSIGNEE);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWithDeliveryStatusAndDestinationWhenChangeDestinationThenDeliveryOrderStatusException() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusDelivery);

        //act && assert
        assertThatThrownBy(() -> deliveryOrderAssigneeService.assigneeOrderToCourier(ID, ASSIGNEE))
                .isInstanceOf(DeliveryOrderStatusException.class)
                .hasMessage("Order status is not Pending");
        verify(orderService, times(1)).findById(ID);
    }


}