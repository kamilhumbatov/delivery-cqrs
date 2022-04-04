package com.delivery.deliver.repository;

import com.delivery.deliver.domain.DeliveryOrderDestination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderDestinationRepository extends JpaRepository<DeliveryOrderDestination, Long> {
}
