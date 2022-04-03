package com.delivery.service.kafka;


import com.delivery.dto.DeliveryOrder;
import com.delivery.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final SendMailService sendMailService;

    @KafkaListener(id = "1", topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void recieveData(DeliveryOrder deliveryOrder) {
        sendMailService.sendStatusChangeMail(deliveryOrder);
    }
}