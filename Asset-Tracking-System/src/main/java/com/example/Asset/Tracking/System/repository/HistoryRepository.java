package com.example.Asset.Tracking.System.repository;

import com.example.Asset.Tracking.System.entity.Asset;
import com.example.Asset.Tracking.System.entity.History;
import com.example.Asset.Tracking.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History,Long> {
    Optional<History> findByUserAndAssetAndEndDateIsNull(User user, Asset asset);
    Optional<History> findByAssetAndEndDateIsNull(Asset asset);
    List<History> findByUserId(Long userId);
    List<History> findByAssetId(Long assetId);
    List<History> findByUserIdAndAssetId(Long userId, Long assetId);
}
