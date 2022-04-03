package com.delivery.deliver.service.kafka;

import com.delivery.deliver.domain.DeliveryOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    public void sendOrderStatus(DeliveryOrder deliveryOrder) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, topicName);
        kafkaTemplate.send(new GenericMessage<DeliveryOrder>(deliveryOrder, headers));
    }
}

