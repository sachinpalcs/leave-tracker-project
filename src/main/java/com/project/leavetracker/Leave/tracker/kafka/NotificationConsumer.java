package com.project.leavetracker.Leave.tracker.kafka;

import com.project.leavetracker.Leave.tracker.dto.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {
    @KafkaListener(topics = "leave-notifications", groupId = "leave-tracker-group")
    public void handleNotification(NotificationEvent event) {
        log.info("CONSUMED EVENT: Sending email to {}", event.getRecipientEmail());

        try {
            Thread.sleep(2000); // Sleep for 2 seconds to prove async works
            log.info("Email sent successfully: {}", event.getSubject());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
