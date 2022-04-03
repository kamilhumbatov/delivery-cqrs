package com.delivery.service;

import com.delivery.dto.DeliveryOrder;

public interface SendMailService {
    void sendStatusChangeMail(DeliveryOrder deliveryOrder);
}