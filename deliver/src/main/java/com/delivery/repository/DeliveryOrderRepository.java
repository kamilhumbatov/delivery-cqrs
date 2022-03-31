package com.delivery.repository;

import com.delivery.domain.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {

    List<DeliveryOrder> findAllOrdersByOwner(String owner);

    List<DeliveryOrder> findAllByAssignee(String assignee);
}
