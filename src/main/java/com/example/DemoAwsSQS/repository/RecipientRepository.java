package com.example.DemoAwsSQS.repository;

import com.example.DemoAwsSQS.entity.RecipientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface RecipientRepository extends R2dbcRepository<RecipientEntity, String> {
}
