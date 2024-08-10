package com.example.exercise.repository;

import com.example.exercise.entity.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PhoneRepository extends CrudRepository<Phone, UUID> {
}
