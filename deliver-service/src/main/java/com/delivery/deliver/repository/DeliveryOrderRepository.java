package com.delivery.deliver.repository;

import com.delivery.deliver.domain.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, String> {

    Optional<DeliveryOrder> findByIdAndOwner(String id, String owner);

    Optional<DeliveryOrder> findByIdAndAssignee(String id, String assignee);

    List<DeliveryOrder> findAllByOwner(String owner);

    List<DeliveryOrder> findAllByAssignee(String assignee);
}
