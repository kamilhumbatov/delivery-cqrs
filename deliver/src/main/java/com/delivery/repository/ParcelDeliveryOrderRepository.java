package com.delivery.repository;

import com.delivery.domain.ParcelDeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelDeliveryOrderRepository extends JpaRepository<ParcelDeliveryOrder, Long> {

    List<ParcelDeliveryOrder> findAllOrdersByOwner(String owner);

    List<ParcelDeliveryOrder> findAllByAssignee(String assignee);
}
