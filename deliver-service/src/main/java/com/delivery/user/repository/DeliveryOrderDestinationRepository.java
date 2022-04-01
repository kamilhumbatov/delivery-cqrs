package com.delivery.user.repository;

import com.delivery.user.domain.DeliveryOrderDestination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderDestinationRepository extends JpaRepository<DeliveryOrderDestination, Long> {
}
