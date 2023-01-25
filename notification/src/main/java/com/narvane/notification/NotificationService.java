package com.narvane.notification;

import com.narvane.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public void sendNotification(NotificationRequest notificationRequest) {
        repository.saveAndFlush(Notification.builder()
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .sender("System")
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .build());
    }

}
