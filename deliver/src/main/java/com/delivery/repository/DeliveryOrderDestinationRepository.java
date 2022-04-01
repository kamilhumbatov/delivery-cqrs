package com.delivery.repository;

import com.delivery.domain.DeliveryOrderDestination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderDestinationRepository extends JpaRepository<DeliveryOrderDestination, Long> {
}
