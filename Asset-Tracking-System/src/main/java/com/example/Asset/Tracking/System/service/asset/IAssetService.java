package com.example.Asset.Tracking.System.service.asset;

import com.example.Asset.Tracking.System.dto.AssetDto;
import com.example.Asset.Tracking.System.entity.Asset;
import com.example.Asset.Tracking.System.enums.AssetStatus;
import com.example.Asset.Tracking.System.request.AddAssetRequest;
import com.example.Asset.Tracking.System.request.UpdateAssetRequest;

import java.util.List;

public interface IAssetService {
    List<Asset> findByName(String name);

    Asset findBySerialNumber(String serialNumber);

    Asset addAsset(AddAssetRequest asset);

    // find all assets
    List<Asset> getAllAssets();

    // find asset by id
    Asset getAssetById(Long id);

    // delete asset by id
    Asset deleteAsset(Long id);

    // delete asset by serial number
    Asset deleteAssetBySerialNumber(String serialNumber);


    // update asset
    Asset updateAsset(Long id, UpdateAssetRequest asset);

    List<Asset> getAssetsByCategoryName(String name);

    AssetDto toAssetDto(Asset asset);

    List<AssetDto> convertAllAssetsToDto(List<Asset> assets);

    void updateAssetStatus(Long id, AssetStatus status);
}
