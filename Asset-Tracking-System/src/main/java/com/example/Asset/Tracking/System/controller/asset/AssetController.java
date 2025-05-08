package com.example.Asset.Tracking.System.controller.asset;

import com.example.Asset.Tracking.System.dto.AssetDto;
import com.example.Asset.Tracking.System.entity.Asset;
import com.example.Asset.Tracking.System.request.AddAssetRequset;
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

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        List<AssetDto> assetDtos = assetService.convertAllAssetsToDto(assets);
        return ResponseEntity.ok(new ApiResponse("success",assetDtos));
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAsset(@RequestBody AddAssetRequset asset) {
        Asset savedAsset = assetService.addAsset(asset);
        AssetDto assetDto = assetService.toAssetDto(savedAsset);
        return ResponseEntity.ok(new ApiResponse("success",assetDto));
    }
}
