package com.delivery.user.repository;

import com.delivery.user.domain.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {

    List<DeliveryOrder> findAllByOwner(String owner);

    List<DeliveryOrder> findAllByAssignee(String assignee);
}
