package com.example.exercise.repository;

import com.example.exercise.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findUserByName(String username);
}
