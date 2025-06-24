package com.example.Asset.Tracking.System.dto;

import com.example.Asset.Tracking.System.entity.Asset;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private String name;
    private List<Asset> assets;
}
