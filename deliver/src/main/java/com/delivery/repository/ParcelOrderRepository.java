package com.delivery.repository;

import com.delivery.domain.ParcelOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelOrderRepository extends JpaRepository<ParcelOrder, Long> {

    List<ParcelOrder> findAllByAssignee(String assignee);
}
