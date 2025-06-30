package com.example.Asset.Tracking.System.repository;

import com.example.Asset.Tracking.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
