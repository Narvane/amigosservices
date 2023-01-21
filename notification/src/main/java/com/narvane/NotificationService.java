package com.narvane;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public void sendNotification(Notification notification) {
        repository.saveAndFlush(notification);
    }

}
