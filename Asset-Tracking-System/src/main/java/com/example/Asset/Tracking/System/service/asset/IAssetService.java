package com.example.Asset.Tracking.System.service.asset;

import com.example.Asset.Tracking.System.dto.AssetDto;
import com.example.Asset.Tracking.System.entity.Asset;
import com.example.Asset.Tracking.System.request.AddAssetRequset;

import java.util.List;

public interface IAssetService {
    List<Asset> findByName(String name);

    Asset findBySerialNumber(String serialNumber);

    Asset addAsset(AddAssetRequset asset);

    // find all assets
    List<Asset> getAllAssets();

    // find asset by id
    Asset getAssetById(Long id);

    // delete asset by id
    Asset deleteAsset(Long id);

    // delete asset by serial number
    Asset deleteAssetBySerialNumber(String serialNumber);

    // update asset
    Asset updateAsset(Long id, Asset asset);

    AssetDto toAssetDto(Asset asset);

    List<AssetDto> convertAllAssetsToDto(List<Asset> assets);
}
