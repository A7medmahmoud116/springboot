package com.example.Asset.Tracking.System.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAssetRequest {
    private String name;
    private String description;
    private String serialNumber;
    private String Status;
}
