package com.delivery.service.impl;

import com.delivery.dto.ParcelOrderDto;
import com.delivery.repository.ParcelOrderRepository;
import com.delivery.service.ParcelOrderService;
import com.delivery.service.mapper.ParcelOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParcelOrderServiceImpl implements ParcelOrderService {

    private final ParcelOrderRepository repository;

    @Override
    public List<ParcelOrderDto> findAllByAssignee(String assignee) {
        return repository.findAllByAssignee(assignee)
                .stream()
                .map(ParcelOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
