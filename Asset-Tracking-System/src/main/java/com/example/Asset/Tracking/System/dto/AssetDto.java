package com.example.Asset.Tracking.System.dto;

import com.example.Asset.Tracking.System.enums.AssetStatus;
import lombok.Data;

@Data
public class AssetDto {
    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private AssetStatus status;
}
