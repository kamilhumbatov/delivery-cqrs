package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderStatusException;
import com.delivery.deliver.repository.DeliveryOrderDestinationRepository;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.commands.OrderCommandService;
import com.delivery.deliver.util.SecurityUtil;
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
class DeliveryOrderStatusServiceImplTest {

    private static final String ID = "121212";
    private static final String RESULT = "12-12";
    private DeliveryOrder deliveryOrderStatusPending;
    private DeliveryOrder deliveryOrderStatusPickup;
    private DeliveryOrder deliveryOrderStatusDelivery;
    private DeliveryOrder deliveryOrderStatusDelivered;
    private DeliveryOrder deliveryOrderStatusCancel;

    @InjectMocks
    DeliveryOrderStatusServiceImpl deliveryOrderStatusService;

    @Mock
    SecurityUtil securityUtil;

    @Mock
    DeliveryOrderService orderService;

    @Mock
    OrderCommandService orderCommand;

    @Mock
    DeliveryOrderDestinationRepository repository;

    @BeforeEach
    void setup() {
        deliveryOrderStatusPending = DeliveryOrder.builder()
                .id(ID)
                .status(DeliveryOrderStatus.PENDING)
                .build();
        deliveryOrderStatusPickup = DeliveryOrder.builder()
                .id(ID)
                .status(DeliveryOrderStatus.PICKUP)
                .build();
        deliveryOrderStatusDelivery = DeliveryOrder.builder()
                .id(ID)
                .status(DeliveryOrderStatus.DELIVERY)
                .build();
        deliveryOrderStatusDelivered = DeliveryOrder.builder()
                .id(ID)
                .status(DeliveryOrderStatus.DELIVERED)
                .build();
        deliveryOrderStatusCancel = DeliveryOrder.builder()
                .id(ID)
                .status(DeliveryOrderStatus.CANCELED)
                .build();
    }

    @Test
    void givenOrderWhenChangeStatusToPickupThenOk() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusPending);
        when(orderCommand.changeStatus(ID, DeliveryOrderStatus.PICKUP)).thenReturn(RESULT);

        //act
        String response = deliveryOrderStatusService.changeOrderStatusToPickUp(ID);

        //assert
        assertThat(response).isEqualTo(RESULT);
        verify(orderCommand, times(1)).changeStatus(ID, DeliveryOrderStatus.PICKUP);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWithCancelWhenChangeStatusToPickupThenDeliveryOrderStatusException() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusCancel);

        //act && assert
        assertThatThrownBy(() -> deliveryOrderStatusService.changeOrderStatusToPickUp(ID))
                .isInstanceOf(DeliveryOrderStatusException.class);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWhenChangeStatusToDeliveryThenOk() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusPickup);
        when(orderCommand.changeStatus(ID, DeliveryOrderStatus.DELIVERY)).thenReturn(RESULT);

        //act
        String response = deliveryOrderStatusService.changeOrderStatusToDelivery(ID);

        //assert
        assertThat(response).isEqualTo(RESULT);
        verify(orderCommand, times(1)).changeStatus(ID, DeliveryOrderStatus.DELIVERY);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWithCancelWhenChangeStatusToDeliveryThenDeliveryOrderStatusException() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusCancel);

        //act && assert
        assertThatThrownBy(() -> deliveryOrderStatusService.changeOrderStatusToDelivery(ID))
                .isInstanceOf(DeliveryOrderStatusException.class);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWhenChangeStatusToDeliveredThenOk() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusDelivery);
        when(orderCommand.changeStatus(ID, DeliveryOrderStatus.DELIVERED)).thenReturn(RESULT);

        //act
        String response = deliveryOrderStatusService.changeOrderStatusToDelivered(ID);

        //assert
        assertThat(response).isEqualTo(RESULT);
        verify(orderCommand, times(1)).changeStatus(ID, DeliveryOrderStatus.DELIVERED);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWithCancelWhenChangeStatusToDeliveredThenDeliveryOrderStatusException() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusCancel);

        //act && assert
        assertThatThrownBy(() -> deliveryOrderStatusService.changeOrderStatusToDelivered(ID))
                .isInstanceOf(DeliveryOrderStatusException.class);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWithWhenChangeStatusToCancelThenOk() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusDelivery);
        when(orderCommand.changeStatus(ID, DeliveryOrderStatus.CANCELED)).thenReturn(RESULT);

        //act
        String response = deliveryOrderStatusService.changeOrderStatusToCancel(ID);

        //assert
        assertThat(response).isEqualTo(RESULT);
        verify(orderCommand, times(1)).changeStatus(ID, DeliveryOrderStatus.CANCELED);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWithDeliveredWhenChangeStatusToDeliveredThenDeliveryOrderStatusException() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusDelivered);

        //act && assert
        assertThatThrownBy(() -> deliveryOrderStatusService.changeOrderStatusToCancel(ID))
                .isInstanceOf(DeliveryOrderStatusException.class);
        verify(orderService, times(1)).findById(ID);
    }
}