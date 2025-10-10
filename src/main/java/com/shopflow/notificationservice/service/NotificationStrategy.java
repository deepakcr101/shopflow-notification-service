package com.shopflow.notificationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

public interface NotificationStrategy {
    void sendNotification(String userId, String message);
}

@Service
class EmailNotificationStrategy implements NotificationStrategy {
    private static final Logger log = LoggerFactory.getLogger(EmailNotificationStrategy.class);
    
    @Override
    public void sendNotification(String userId, String message) {
        log.info("Sending email notification to user {}: {}", userId, message);
        // Implement actual email sending logic here
    }
}

@Service
class SMSNotificationStrategy implements NotificationStrategy {
    private static final Logger log = LoggerFactory.getLogger(SMSNotificationStrategy.class);
    
    @Override
    public void sendNotification(String userId, String message) {
        log.info("Sending SMS notification to user {}: {}", userId, message);
        // Implement actual SMS sending logic here
    }
}