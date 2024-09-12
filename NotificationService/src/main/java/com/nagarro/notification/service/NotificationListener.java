package com.nagarro.notification.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener(queues = "notificationQueue")
    public void receiveMessage(String message) {
        System.out.println("Received notification: " + message);
    }
}

