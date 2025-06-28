package com.example.Asset.Tracking.System.dto;

import com.example.Asset.Tracking.System.entity.AssetAssignment;
import com.example.Asset.Tracking.System.entity.Category;
import com.example.Asset.Tracking.System.enums.AssetStatus;
import lombok.Data;

import java.util.List;

@Data
public class AssetDto {
    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private AssetStatus status;
    private Category category;
}
