package com.example.Asset.Tracking.System.request;

import com.example.Asset.Tracking.System.entity.Category;
import lombok.Data;

@Data
public class AddAssetRequest {
    private long id;
    private String name;
    private String description;
    private String serialNumber;
    private Category category;
}
