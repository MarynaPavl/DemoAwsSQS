package com.example.DemoAwsSQS.poller;

import com.example.DemoAwsSQS.dto.NotificationDto;
import com.example.DemoAwsSQS.entity.NotificationEntity;
import com.example.DemoAwsSQS.mapper.NotificationMapper;
import com.example.DemoAwsSQS.repository.NotificationRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationSQSPoller {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    @SqsListener(value = "${sqs.notifications.queue.name}")
    public void receiveMessage(@Payload NotificationDto message) {
        log.info("received notification: {}", message);
        NotificationEntity entity = notificationMapper.map(message);
        notificationRepository.save(entity).subscribe();
    }
}
