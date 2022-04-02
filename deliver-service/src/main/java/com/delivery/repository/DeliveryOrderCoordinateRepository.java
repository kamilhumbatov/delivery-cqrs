package com.delivery.repository;

import com.delivery.domain.DeliveryOrderCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderCoordinateRepository extends JpaRepository<DeliveryOrderCoordinate,Long> {
}
