package com.example.Asset.Tracking.System.service.asset;

import com.example.Asset.Tracking.System.dto.AssetDto;
import com.example.Asset.Tracking.System.entity.Asset;
import com.example.Asset.Tracking.System.enums.AssetStatus;
import com.example.Asset.Tracking.System.exceptions.AlreadyExistException;
import com.example.Asset.Tracking.System.exceptions.ResourceNotFound;
import com.example.Asset.Tracking.System.repository.AssetRepository;
import com.example.Asset.Tracking.System.request.AddAssetRequset;
import com.example.Asset.Tracking.System.request.UpdateAssetRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.Asset.Tracking.System.enums.AssetStatus.AVAILABLE;

@Service
@RequiredArgsConstructor
public class AssetService implements IAssetService{
    private final AssetRepository assetRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Asset> findByName(String name) {
        List<Asset> assets = assetRepository
                .findByName(name).orElseThrow(() -> new ResourceNotFound("Asset not found"));
        return assets;
    }
    @Override
    public Asset findBySerialNumber(String serialNumber) {
        Asset asset = assetRepository
                .findBySerialNumber(serialNumber).orElseThrow(() -> new ResourceNotFound("Asset not found"));
        return asset;
    }

    private boolean existsBySerialNumber(String serialNumber) {
        return assetRepository.existsBySerialNumber(serialNumber);
    }

    @Override
    public Asset addAsset(AddAssetRequset asset) {
        if (existsBySerialNumber(asset.getSerialNumber())) {
            throw new AlreadyExistException("Asset already exists");
        }
        Asset savedAsset = new Asset();
        savedAsset.setName(asset.getName());
        savedAsset.setDescription(asset.getDescription());
        savedAsset.setSerialNumber(asset.getSerialNumber());
        savedAsset.setStatus(AVAILABLE);
        return assetRepository.save(savedAsset);
    }
    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
    @Override
    public Asset getAssetById(Long id) {
        Optional<Asset> asset = assetRepository.findById(id);
        return asset.orElseThrow(() -> new ResourceNotFound("Asset not found"));
    }
    @Override
    public Asset deleteAsset(Long id) {
        Asset asset = getAssetById(id);
        assetRepository.delete(asset);
        return asset;
    }

    // delete asset by serial number
    @Override
    public Asset deleteAssetBySerialNumber(String serialNumber) {
        Asset asset = findBySerialNumber(serialNumber);
        assetRepository.delete(asset);
        return asset;
    }
    // update asset
    @Override
    public Asset updateAsset(Long id, UpdateAssetRequest asset) {
        Asset existingAsset = getAssetById(id);
        existingAsset.setName(asset.getName());
        existingAsset.setDescription(asset.getDescription());
        existingAsset.setSerialNumber(asset.getSerialNumber());
        existingAsset.setStatus(AssetStatus.valueOf(asset.getStatus()));
        return assetRepository.save(existingAsset);
    }
    @Override
    public AssetDto toAssetDto(Asset asset) {
        return modelMapper.map(asset, AssetDto.class);
    }
    @Override
    public List<AssetDto> convertAllAssetsToDto(List<Asset> assets) {
        return assets.stream().map(this::toAssetDto).toList();
    }



}
