package com.khaled.project.common.listener;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.khaled.project.common.entity.Notification;
import com.khaled.project.common.exception.MapperException;
import com.khaled.project.common.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {

    @Autowired
    private NotificationRepository notificationRepository;
    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    SimpMessagingTemplate template;
    @KafkaListener(topics = "notification-event", groupId = "group_id_1")
    public void listenSenderEmail(String data) {

        Notification notification = fromJson(data, Notification.class);
        log.info("Consumed message: " + data);
        template.convertAndSend("/topic/notif", notification);
        notificationRepository.save(notification);
      //  template.convertAndSend();

    }

    private <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}
