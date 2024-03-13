package com.example.DemoAwsSQS.mapper;

import com.example.DemoAwsSQS.dto.NotificationDto;
import com.example.DemoAwsSQS.entity.NotificationEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDto map(NotificationEntity entity);

    @InheritInverseConfiguration
    NotificationEntity map(NotificationDto dto);
}
