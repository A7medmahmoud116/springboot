package com.example.Asset.Tracking.System.repository;

import com.example.Asset.Tracking.System.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    Optional<List<Asset>> findByName(String name);

    Optional<Asset> findBySerialNumber(String serialNumber);

    boolean existsBySerialNumber(String serialNumber);

    List<Asset> findByCategory_Name(String name);
}
