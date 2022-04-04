package com.delivery.service.impl;

import com.delivery.config.MailConfig;
import com.delivery.dto.DeliveryOrder;
import com.delivery.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {

    @Value("${spring.mail.username}")
    private String hostMail;

    private final MailConfig mailConfig;

    @Override
    public void sendStatusChangeMail(DeliveryOrder deliveryOrder) {
        var message = new SimpleMailMessage();
        message.setFrom(hostMail);
        message.setTo(deliveryOrder.getOwner());
        message.setSubject("Delivery status");
        message.setText(String.format("You parcel delivery status is %s", deliveryOrder.getStatus()));
        mailConfig.getJavaMailSender().send(message);
    }
}

