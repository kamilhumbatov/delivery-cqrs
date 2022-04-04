package com.delivery.deliver.repository;

import com.delivery.deliver.domain.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, String> {

    List<DeliveryOrder> findAllByOwner(String owner);

    List<DeliveryOrder> findAllByAssignee(String assignee);
}
