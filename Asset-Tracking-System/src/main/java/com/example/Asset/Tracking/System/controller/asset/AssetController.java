package com.example.Asset.Tracking.System.controller.asset;

import com.example.Asset.Tracking.System.dto.AssetDto;
import com.example.Asset.Tracking.System.entity.Asset;
import com.example.Asset.Tracking.System.request.AddAssetRequest;
import com.example.Asset.Tracking.System.request.UpdateAssetRequest;
import com.example.Asset.Tracking.System.response.ApiResponse;
import com.example.Asset.Tracking.System.service.asset.IAssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/asset")
@RequiredArgsConstructor
public class AssetController {

    private final IAssetService assetService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        List<AssetDto> assetDto = assetService.convertAllAssetsToDto(assets);
        return ResponseEntity.ok(new ApiResponse("success",assetDto));
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> addAsset(@RequestBody AddAssetRequest asset) {
        Asset savedAsset = assetService.addAsset(asset);
        AssetDto assetDto = assetService.toAssetDto(savedAsset);
        return ResponseEntity.ok(new ApiResponse("success",assetDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAssetById(@PathVariable Long id) {
        Asset asset = assetService.getAssetById(id);
        AssetDto assetDto = assetService.toAssetDto(asset);
        return ResponseEntity.ok(new ApiResponse("success",assetDto));
    }
    @GetMapping("/serialNumber")
    public ResponseEntity<ApiResponse> getAssetBySerialNumber(@RequestParam String serialNumber) {
        Asset asset = assetService.findBySerialNumber(serialNumber);
        AssetDto assetDto = assetService.toAssetDto(asset);
        return ResponseEntity.ok(new ApiResponse("success",assetDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAsset(@PathVariable Long id,@RequestBody UpdateAssetRequest asset) {
        Asset updatedAsset = assetService.updateAsset(id,asset);
        AssetDto assetDto = assetService.toAssetDto(updatedAsset);
        return ResponseEntity.ok(new ApiResponse("Asset updated",assetDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAsset(@PathVariable Long id) {
        Asset deletedAsset = assetService.deleteAsset(id);
        AssetDto assetDto = assetService.toAssetDto(deletedAsset);
        return ResponseEntity.ok(new ApiResponse("Asset deleted",deletedAsset));
    }
    @DeleteMapping("/serialNumber")
    public ResponseEntity<ApiResponse> deleteAssetBySerialNumber(@RequestParam String serialNumber) {
        Asset deletedAsset = assetService.deleteAssetBySerialNumber(serialNumber);
        AssetDto assetDto = assetService.toAssetDto(deletedAsset);
        return ResponseEntity.ok(new ApiResponse("Asset deleted",assetDto));
    }
    @GetMapping("/name")
    public ResponseEntity<ApiResponse> getAssetsByCategoryName(@RequestParam String name){
        List<Asset> assets = assetService.getAssetsByCategoryName(name);
        List<AssetDto> assetDto = assetService.convertAllAssetsToDto(assets);
        return ResponseEntity.ok(new ApiResponse("success",assetDto));
    }

}
