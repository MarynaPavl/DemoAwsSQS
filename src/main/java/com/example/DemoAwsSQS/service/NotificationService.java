package com.example.DemoAwsSQS.service;

import com.example.DemoAwsSQS.dto.NotificationDto;
import com.example.DemoAwsSQS.mapper.NotificationMapper;
import com.example.DemoAwsSQS.repository.NotificationRepository;
import com.example.DemoAwsSQS.repository.RecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final RecipientRepository recipientRepository;
    private final NotificationMapper notificationMapper;

    public Mono<NotificationDto> findNotificationByUid(String uid){
        return notificationRepository.findById(uid)
                                     .map(notificationMapper::map);
    }

    public Mono<NotificationDto> findNotificationWithRecipientByUid(String uid){
        return notificationRepository.findById(uid)
                                     .flatMap(notificationEntiti -> recipientRepository.findById(notificationEntiti.getRecipientUid())
                                                                                       .map(recipientEntity -> {
                                                                                           notificationEntiti.setRecipient(recipientEntity);
                                                                                           return notificationEntiti;
                                                                                       }).map(notificationMapper::map));

    }
}
