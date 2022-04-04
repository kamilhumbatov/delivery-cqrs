package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.repository.DeliveryOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DeliveryOrderServiceImplTest {

    private DeliveryOrder deliveryOrder;
    public static final String ID="121212";

    @InjectMocks
    private DeliveryOrderServiceImpl deliveryOrderService;

    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;

    @BeforeEach
    void setup() {
        deliveryOrder = DeliveryOrder.builder()
                .id(ID)
                .build();
    }

    @Test
    void findById() {
        //arrange
        when(deliveryOrderRepository.findById(ID)).thenReturn(Optional.of(deliveryOrder));

        //act
        DeliveryOrder deliveryOrder=deliveryOrderService.findById(ID);

        //assert
        assertThat(deliveryOrder.getId()).isEqualTo(deliveryOrder.getId());
        verify(deliveryOrderRepository, times(1)).findById(ID);
    }
}