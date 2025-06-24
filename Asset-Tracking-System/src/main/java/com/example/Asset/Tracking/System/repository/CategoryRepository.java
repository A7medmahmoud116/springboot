package com.example.Asset.Tracking.System.repository;

import com.example.Asset.Tracking.System.entity.Category;
import com.example.Asset.Tracking.System.request.AddCategoryRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
    void deleteById(Long id);
    void deleteByName(String name);
}
