package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.exception.DeliveryOrderNotFoundException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DeliveryOrderServiceImplTest {

    private DeliveryOrder deliveryOrder;
    private DeliveryOrder deliveryOrderForSave;
    private static final String ID = "121212";
    private static final String OWNER = "customer";
    private static final String ASSIGNEE = "courier";
    private static List<DeliveryOrder> deliveryOrderList;

    @InjectMocks
    DeliveryOrderServiceImpl deliveryOrderService;

    @Mock
    DeliveryOrderRepository deliveryOrderRepository;

    @BeforeEach
    void setup() {
        deliveryOrder = DeliveryOrder.builder()
                .id(ID)
                .assignee(ASSIGNEE)
                .owner(OWNER)
                .build();

        deliveryOrderForSave = DeliveryOrder.builder()
                .id(ID)
                .assignee(ASSIGNEE)
                .owner(OWNER)
                .build();
        deliveryOrderList = Arrays.asList(deliveryOrder);
    }

    @Test
    void givenIdWhenCallFindByIdThenOk() {
        //arrange
        when(deliveryOrderRepository.findById(ID)).thenReturn(Optional.of(deliveryOrder));

        //act
        DeliveryOrder order = deliveryOrderService.findById(ID);

        //assert
        assertThat(order.getId()).isEqualTo(deliveryOrder.getId());
        verify(deliveryOrderRepository, times(1)).findById(ID);
    }

    @Test
    void givenIdWhenCallFindByIdThenDeliveryOrderNotFoundException() {
        //arrange
        when(deliveryOrderRepository.findById(ID)).thenThrow(new DeliveryOrderNotFoundException(ID));

        //act && assert
        assertThatThrownBy(() -> deliveryOrderService.findById(ID))
                .isInstanceOf(DeliveryOrderNotFoundException.class)
                .hasMessage(String.format("Invalid DeliveryOrder id %s", ID));
        verify(deliveryOrderRepository, times(1)).findById(ID);
    }

    @Test
    void givenIdAndOwnerWhenCallFindByIdThenOk() {
        //arrange
        when(deliveryOrderRepository.findByIdAndOwner(ID, OWNER)).thenReturn(Optional.of(deliveryOrder));

        //act
        DeliveryOrder order = deliveryOrderService.findByIdAndOwner(ID, OWNER);

        //assert
        assertThat(order.getId()).isEqualTo(deliveryOrder.getId());
        verify(deliveryOrderRepository, times(1)).findByIdAndOwner(ID, OWNER);
    }

    @Test
    void givenIdAndOwnerWhenCallFindByIdThenDeliveryOrderNotFoundException() {
        //arrange
        when(deliveryOrderRepository.findByIdAndOwner(ID, OWNER)).thenThrow(new DeliveryOrderNotFoundException(ID));

        //act && assert
        assertThatThrownBy(() -> deliveryOrderService.findByIdAndOwner(ID, OWNER))
                .isInstanceOf(DeliveryOrderNotFoundException.class)
                .hasMessage(String.format("Invalid DeliveryOrder id %s", ID));
        verify(deliveryOrderRepository, times(1)).findByIdAndOwner(ID, OWNER);
    }

    @Test
    void givenIdAndAssigneeWhenCallFindByIdThenOk() {
        //arrange
        when(deliveryOrderRepository.findByIdAndAssignee(ID, ASSIGNEE)).thenReturn(Optional.of(deliveryOrder));

        //act
        DeliveryOrder order = deliveryOrderService.findByIdAndAssignee(ID, ASSIGNEE);

        //assert
        assertThat(order.getId()).isEqualTo(deliveryOrder.getId());
        verify(deliveryOrderRepository, times(1)).findByIdAndAssignee(ID, ASSIGNEE);
    }

    @Test
    void givenIdAndAssigneeWhenCallFindByIdThenDeliveryOrderNotFoundException() {
        //arrange
        when(deliveryOrderRepository.findByIdAndAssignee(ID, OWNER)).thenThrow(new DeliveryOrderNotFoundException(ID));

        //act && assert
        assertThatThrownBy(() -> deliveryOrderService.findByIdAndAssignee(ID, OWNER))
                .isInstanceOf(DeliveryOrderNotFoundException.class)
                .hasMessage(String.format("Invalid DeliveryOrder id %s", ID));
        verify(deliveryOrderRepository, times(1)).findByIdAndAssignee(ID, OWNER);
    }

    @Test
    void givenOwnerWhenFindByOwnerThenSuccess() {
        //arrange
        when(deliveryOrderRepository.findAllByOwner(OWNER)).thenReturn(deliveryOrderList);

        //act
        List<DeliveryOrder> orderList = deliveryOrderService.findAllOrdersByOwner(OWNER);

        //assert
        assertThat(orderList.size()).isEqualTo(deliveryOrderList.size());
        assertThat(orderList.get(0).getId()).isEqualTo(deliveryOrder.getId());
        assertThat(orderList.get(0).getAssignee()).isEqualTo(deliveryOrder.getAssignee());
        assertThat(orderList.get(0).getOwner()).isEqualTo(deliveryOrder.getOwner());
        verify(deliveryOrderRepository, times(1)).findAllByOwner(OWNER);
    }

    @Test
    void givenAssigneeWhenFindByOwnerThenSuccess() {
        //arrange
        when(deliveryOrderRepository.findAllByAssignee(ASSIGNEE)).thenReturn(deliveryOrderList);

        //act
        List<DeliveryOrder> orderList = deliveryOrderService.findAllOrdersByAssignee(ASSIGNEE);

        //assert
        assertThat(orderList.size()).isEqualTo(deliveryOrderList.size());
        assertThat(orderList.get(0).getId()).isEqualTo(deliveryOrder.getId());
        assertThat(orderList.get(0).getAssignee()).isEqualTo(deliveryOrder.getAssignee());
        assertThat(orderList.get(0).getOwner()).isEqualTo(deliveryOrder.getOwner());
        verify(deliveryOrderRepository, times(1)).findAllByAssignee(ASSIGNEE);
    }

    @Test
    void givenDeliveryOrderWhenSaveThenSuccess() {
        //arrange
        when(deliveryOrderRepository.save(deliveryOrderForSave)).thenReturn(deliveryOrder);

        //act
        DeliveryOrder order = deliveryOrderService.save(deliveryOrderForSave);

        //assert
        assertThat(order.getId()).isEqualTo(deliveryOrder.getId());
        verify(deliveryOrderRepository, times(1)).save(deliveryOrderForSave);
    }
}