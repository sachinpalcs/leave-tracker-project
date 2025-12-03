package com.project.leavetracker.Leave.tracker.kafka;

import com.project.leavetracker.Leave.tracker.dto.NotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;
    private static final String TOPIC = "leave-notifications";

    public void sendNotification(NotificationEvent event) {
        log.info("Sending notification event to Kafka for request ID: {}", event.getLeaveRequestId());
        kafkaTemplate.send(TOPIC, event);
    }
}
