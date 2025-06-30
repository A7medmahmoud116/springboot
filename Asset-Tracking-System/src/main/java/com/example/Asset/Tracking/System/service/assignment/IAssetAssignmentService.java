package com.example.Asset.Tracking.System.service.assignment;

import com.example.Asset.Tracking.System.entity.AssetAssignment;
import com.example.Asset.Tracking.System.request.AssignAssetRequest;

public interface IAssetAssignmentService {
    AssetAssignment assignAssetToUser(AssignAssetRequest request);

    public void deleteAssetAssignmentByAssetId(Long assignmentId);

    void sendAssetToMaintenance(Long assetId);

    void completeAssetMaintenance(Long assetId);
}
