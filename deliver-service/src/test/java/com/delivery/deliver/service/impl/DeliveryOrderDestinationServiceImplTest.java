package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.domain.DeliveryOrderDestination;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderNotFoundException;
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
class DeliveryOrderDestinationServiceImplTest {

    private static final String ID = "121212";
    private static final Long ID_DESTINATION = 1L;
    private static final String RESULT = "12-12";
    private DeliveryOrder deliveryOrderStatusPending;
    private DeliveryOrder deliveryOrderStatusDelivery;
    private DeliveryOrderDestination deliveryOrderDestination;
    private DeliveryOrderDestination deliveryOrderDestinationForSave;
    private DeliveryOrderDestinationDto deliveryOrderDestinationDto;

    @InjectMocks
    DeliveryOrderDestinationServiceImpl deliveryOrderDestinationService;

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
        deliveryOrderDestination = DeliveryOrderDestination.builder()
                .id(ID_DESTINATION)
                .latitude("")
                .longitude("")
                .build();
        deliveryOrderDestinationForSave = DeliveryOrderDestination.builder()
                .id(ID_DESTINATION)
                .latitude("")
                .longitude("")
                .build();
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
    void givenOrderWithPendingStatusAndDestinationWhenChangeDestinationThenOk() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusPending);
        when(orderCommand.changeDestination(ID, deliveryOrderDestinationDto)).thenReturn(RESULT);

        //act
        String response = deliveryOrderDestinationService.changeDestination(ID, deliveryOrderDestinationDto);

        //assert
        assertThat(response).isEqualTo(RESULT);
        verify(orderCommand, times(1)).changeDestination(ID, deliveryOrderDestinationDto);
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenOrderWithDeliveryStatusAndDestinationWhenChangeDestinationThenDeliveryOrderStatusException() {
        //arrange
        when(orderService.findById(ID)).thenReturn(deliveryOrderStatusDelivery);

        //act && assert
        assertThatThrownBy(() -> deliveryOrderDestinationService.changeDestination(ID, deliveryOrderDestinationDto))
                .isInstanceOf(DeliveryOrderStatusException.class)
                .hasMessage("Order status is not Pending");
        verify(orderService, times(1)).findById(ID);
    }

    @Test
    void givenDestinationWhenUpdateDestinationThenOk() {
        //arrange
        when(repository.save(deliveryOrderDestinationForSave)).thenReturn(deliveryOrderDestination);

        //act
        DeliveryOrderDestination orderDestination = deliveryOrderDestinationService
                .updateDestination(deliveryOrderDestinationForSave);

        //assert
        assertThat(orderDestination.getId()).isEqualTo(deliveryOrderDestination.getId());
        verify(repository, times(1)).save(deliveryOrderDestinationForSave);
    }
}