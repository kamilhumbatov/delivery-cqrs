package com.delivery.service;

import com.delivery.dto.ParcelOrderDto;

import java.util.List;

public interface ParcelOrderService {
    List<ParcelOrderDto> findAllByAssignee(String assignee);
}
