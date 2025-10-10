package com.shopflow.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopflow.notificationservice.model.PaymentSuccessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    
    private final ObjectMapper objectMapper;
    private final List<NotificationStrategy> notificationStrategies;

    public NotificationService(ObjectMapper objectMapper, List<NotificationStrategy> notificationStrategies) {
        this.objectMapper = objectMapper;
        this.notificationStrategies = notificationStrategies;
    }

    @KafkaListener(topics = "payment_success_events", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePaymentSuccessEvent(String eventJson) {
        try {
            PaymentSuccessEvent event = objectMapper.readValue(eventJson, PaymentSuccessEvent.class);
            log.info("Received payment success event for order ID: {}", event.getOrderId());
            
            String message = String.format("Your payment for order %s of amount %s has been processed successfully.", 
                event.getOrderId(), event.getOrderAmount());

            // Send notification through all available channels
            notificationStrategies.forEach(strategy -> 
                strategy.sendNotification(event.getUserId(), message));

        } catch (Exception e) {
            log.error("Error processing payment success event: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process payment success event", e);
        }
    }
}
