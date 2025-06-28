package com.example.Asset.Tracking.System.repository;

import com.example.Asset.Tracking.System.entity.AssetAssignment;
import com.example.Asset.Tracking.System.enums.AssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface AssetAssignmentRepository extends JpaRepository<AssetAssignment, Long> {

    @Query("SELECT a FROM AssetAssignment a WHERE a.asset.id = :assetId AND a.asset.status = :status")
    Optional<AssetAssignment> findActiveAssignmentByAssetId(@Param("assetId") Long assetId, @Param("status") AssetStatus status);

    //Optional<AssetAssignment> findByAssetIdAndStatus(Long assetId, AssetStatus status);
}
