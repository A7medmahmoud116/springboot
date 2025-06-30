package com.example.Asset.Tracking.System.controller.assignment;
import com.example.Asset.Tracking.System.entity.User;
import com.example.Asset.Tracking.System.enums.Role;
import com.example.Asset.Tracking.System.response.ApiResponse;
import com.example.Asset.Tracking.System.service.assignment.AssetAssignmentService;
import com.example.Asset.Tracking.System.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.Asset.Tracking.System.entity.AssetAssignment;
import com.example.Asset.Tracking.System.request.AssignAssetRequest;
import org.springframework.http.ResponseEntity;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/assignment")
public class AssetAssignmentController {
    private final AssetAssignmentService assetAssignmentService;
    private final UserService userService;

    @PostMapping("/assign")
    public ResponseEntity<ApiResponse> assignAsset(@RequestBody AssignAssetRequest request) {
        AssetAssignment assignment = assetAssignmentService.assignAssetToUser(request);
        return ResponseEntity.ok(new ApiResponse("success",assignment));
    }

    @DeleteMapping("/{assetId}")
    public ResponseEntity<ApiResponse> deleteAssignmentByAssetId(@PathVariable Long assetId) {
        assetAssignmentService.deleteAssetAssignmentByAssetId(assetId);
        return ResponseEntity.ok(new ApiResponse("Asset assignment deleted successfully", null));
    }
    @PutMapping("/{assetId}/maintain")
    public ResponseEntity<ApiResponse> sendAssetToMaintenance(@PathVariable Long assetId) {
        assetAssignmentService.sendAssetToMaintenance(assetId);
        return ResponseEntity.ok(new ApiResponse("Asset sent to maintenance successfully", null));
    }

    @PutMapping("/{assetId}/maintenance-complete")
    public ResponseEntity<ApiResponse> completeAssetMaintenance(@PathVariable Long assetId) {
        assetAssignmentService.completeAssetMaintenance(assetId);
        return ResponseEntity.ok(new ApiResponse("Asset maintenance completed ", null));
    }
}
