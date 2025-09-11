package com.shopflow.notificationservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "payment_success_events", groupId = "notification_group")
    public void handlePaymentSuccessEvent(String orderId) {
        System.out.println("Received payment success event for order ID: " + orderId);
        // Simulate sending email/SMS notification
        System.out.println("Sending notification for order ID: " + orderId);
    }
}
