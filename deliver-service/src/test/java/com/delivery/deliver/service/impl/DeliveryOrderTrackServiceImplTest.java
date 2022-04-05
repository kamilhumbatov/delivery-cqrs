package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderStatusException;
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
class DeliveryOrderTrackServiceImplTest {

    private static final String ID = "121212";
    private static final String RESULT = "12-12";
    private DeliveryOrder deliveryOrderStatusPending;
    private DeliveryOrder deliveryOrderStatusDelivery;
    private DeliveryOrderDestinationDto deliveryOrderDestinationDto;

    @InjectMocks
    DeliveryOrderTrackServiceImpl deliveryOrderTrackService;

    @Mock
    SecurityUtil securityUtil;

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
        deliveryOrderDestinationDto = DeliveryOrderDestinationDto.builder()
                .latitude("")
                .longitude("")
                .build();
    }

    @Test
    void givenOrderWithDeliveryStatusAndDestinationWhenChangeCoordinateThenOk() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusDelivery);
        when(orderCommand.changeCoordinate(ID, deliveryOrderDestinationDto)).thenReturn(RESULT);

        //act
        String response = deliveryOrderTrackService.changeCoordinate(ID, deliveryOrderDestinationDto);

        //assert
        assertThat(response).isEqualTo(RESULT);
        verify(orderCommand, times(1)).changeCoordinate(ID, deliveryOrderDestinationDto);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWithPendingStatusAndDestinationWhenChangeDestinationThenDeliveryOrderStatusException() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusPending);

        //act && assert
        assertThatThrownBy(() -> deliveryOrderTrackService.changeCoordinate(ID, deliveryOrderDestinationDto))
                .isInstanceOf(DeliveryOrderStatusException.class)
                .hasMessage("Order status is not Delivery!");
        verify(orderService, times(1)).findById(ID);
    }
}