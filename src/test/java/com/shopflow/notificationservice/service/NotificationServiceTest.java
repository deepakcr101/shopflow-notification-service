package com.shopflow.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopflow.notificationservice.model.PaymentSuccessEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationStrategy emailStrategy;

    @Mock
    private NotificationStrategy smsStrategy;

    private NotificationService notificationService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        notificationService = new NotificationService(objectMapper, List.of(emailStrategy, smsStrategy));
    }

    @Test
    void handlePaymentSuccessEvent_ShouldSendNotificationsToAllChannels() throws Exception {
        // Arrange
        PaymentSuccessEvent event = new PaymentSuccessEvent();
        event.setOrderId("12345");
        event.setUserId("user123");
        event.setOrderAmount("100.00");
        String eventJson = objectMapper.writeValueAsString(event);

        // Act
        notificationService.handlePaymentSuccessEvent(eventJson);

        // Assert
        String expectedMessage = "Your payment for order 12345 of amount 100.00 has been processed successfully.";
        verify(emailStrategy).sendNotification("user123", expectedMessage);
        verify(smsStrategy).sendNotification("user123", expectedMessage);
    }
}