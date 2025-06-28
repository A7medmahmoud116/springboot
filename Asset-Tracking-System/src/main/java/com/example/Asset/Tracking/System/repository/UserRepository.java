package com.example.Asset.Tracking.System.repository;

import com.example.Asset.Tracking.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
