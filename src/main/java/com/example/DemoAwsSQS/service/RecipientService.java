package com.example.DemoAwsSQS.service;

import com.example.DemoAwsSQS.dto.RecipientDto;
import com.example.DemoAwsSQS.entity.NotificationEntity;
import com.example.DemoAwsSQS.entity.RecipientEntity;
import com.example.DemoAwsSQS.mapper.RecipientMapper;
import com.example.DemoAwsSQS.repository.NotificationRepository;
import com.example.DemoAwsSQS.repository.RecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipientService {
    private final NotificationRepository notificationRepository;
    private final RecipientRepository recipientRepository;
    private final RecipientMapper recipientMapper;

    public Mono<RecipientDto> findRecipientWithNotificationByUid(String uid) {
       return Mono.zip(recipientRepository.findById(uid),
               notificationRepository.findAllByRecipientUid(uid).collectList())
               .map(tuples -> {
                   RecipientEntity recipient = tuples.getT1();
                   List<NotificationEntity> notifications = tuples.getT2();
                   recipient.setNotifications(notifications);
                   return recipientMapper.map(recipient);
               });
    }

}
