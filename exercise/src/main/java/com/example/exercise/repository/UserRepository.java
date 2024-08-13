package com.example.exercise.repository;

import com.example.exercise.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findUserByName(String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.email = :email, u.password = :password WHERE u.id = :userId")
    int updateEmailAndPasswordByUserId(UUID userId, String email, String password);

}
