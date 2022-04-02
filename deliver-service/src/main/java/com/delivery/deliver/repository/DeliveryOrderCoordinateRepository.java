package com.delivery.deliver.repository;

import com.delivery.deliver.domain.DeliveryOrderCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderCoordinateRepository extends JpaRepository<DeliveryOrderCoordinate,Long> {
}
