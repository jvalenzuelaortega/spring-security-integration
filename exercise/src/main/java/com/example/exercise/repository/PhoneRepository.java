package com.example.exercise.repository;

import com.example.exercise.entity.PhoneEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PhoneRepository extends CrudRepository<PhoneEntity, UUID> {
}
