package com.example.DemoAwsSQS.mapper;

import com.example.DemoAwsSQS.dto.RecipientDto;
import com.example.DemoAwsSQS.entity.RecipientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipientMapper {
    RecipientDto map(RecipientEntity entity);

    @InheritInverseConfiguration
    RecipientEntity map(RecipientDto dto);
}
