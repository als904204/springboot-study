package com.example.springsecurityoauth2session.repository;

import com.example.springsecurityoauth2session.entiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
