package com.example.Asset.Tracking.System.service.asset;

import com.example.Asset.Tracking.System.entity.Asset;
import com.example.Asset.Tracking.System.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;

    public List<Asset> findByName(String name) {
        List<Asset> assets = assetRepository
                .findByName(name).orElseThrow(() -> new RuntimeException("Asset not found"));
        return assets;
    }

}
