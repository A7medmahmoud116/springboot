package com.example.Asset.Tracking.System.request;

import lombok.Data;

@Data
public class AddAssetRequset {
    private long id;
    private String name;
    private String description;
    private String serialNumber;
}
