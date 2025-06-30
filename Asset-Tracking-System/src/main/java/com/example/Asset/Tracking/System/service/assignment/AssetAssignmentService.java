package com.example.Asset.Tracking.System.service.assignment;

import com.example.Asset.Tracking.System.entity.Asset;
import com.example.Asset.Tracking.System.entity.AssetAssignment;
import com.example.Asset.Tracking.System.entity.History;
import com.example.Asset.Tracking.System.entity.User;
import com.example.Asset.Tracking.System.enums.AssetStatus;
import com.example.Asset.Tracking.System.enums.Role;
import com.example.Asset.Tracking.System.exceptions.ResourceNotFound;
import com.example.Asset.Tracking.System.repository.HistoryRepository;
import com.example.Asset.Tracking.System.repository.AssetAssignmentRepository;
import com.example.Asset.Tracking.System.repository.UserRepository;
import com.example.Asset.Tracking.System.request.AssignAssetRequest;
import com.example.Asset.Tracking.System.service.asset.IAssetService;
import com.example.Asset.Tracking.System.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.Asset.Tracking.System.enums.AssetStatus.AVAILABLE;
import static com.example.Asset.Tracking.System.enums.AssetStatus.IN_USE;

@Service
@RequiredArgsConstructor
public class AssetAssignmentService implements IAssetAssignmentService {

    private final AssetAssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final IAssetService assetService;
    private final HistoryRepository assetAssignmentHistoryRepository;
    private final UserService userService;

    @Override
    public AssetAssignment assignAssetToUser(AssignAssetRequest request) {
        User user = userService.getAuthenticatedUser();
        Long userId;
        if(user.getRole()== Role.ADMIN)
            userId= request.getUserId();
        else
            userId =user.getId();
        Asset asset = assetService.getAssetById(request.getAssetId());
        if (asset.getStatus() != AVAILABLE) {
            String statusStr = asset.getStatus().name().toLowerCase().replace("_", " ");
            throw new IllegalArgumentException("Cannot assign asset. It is currently " + statusStr + ".");
        }
        LocalDateTime now = LocalDateTime.now();


        AssetAssignment assignment = new AssetAssignment();
        assignment.setUser(user);
        assignment.setAsset(asset);
        assignment.setStartDate(now);
        assignment.setNotes(request.getNotes());
        assetService.updateAssetStatus(request.getAssetId(), IN_USE);

        History history = new History();
        history.setUser(user);
        history.setAsset(asset);
        history.setNotes(request.getNotes());
        history.setStartDate(now);

        AssetAssignment savedAssetAssignment = assignmentRepository.save(assignment);
        assetAssignmentHistoryRepository.save(history);

        return savedAssetAssignment;
    }

    @Override
    public void deleteAssetAssignmentByAssetId(Long assetId) {
        AssetAssignment assignment = assignmentRepository.findActiveAssignmentByAssetId(assetId, IN_USE)
                .orElseThrow(() -> new ResourceNotFound("there is no assignment for this asset"));

        History history = assetAssignmentHistoryRepository
                .findByUserAndAssetAndEndDateIsNull(assignment.getUser(), assignment.getAsset())
                .orElseThrow(() -> new ResourceNotFound("No active history found"));

        history.setEndDate(LocalDateTime.now());
        assetAssignmentHistoryRepository.save(history);

        assignmentRepository.delete(assignment);
        assetService.updateAssetStatus(assetId, AVAILABLE);
    }

    @Override
    public void sendAssetToMaintenance(Long assetId) {
        Asset asset = assetService.findById(assetId);
        if (asset.getStatus() == AssetStatus.IN_USE) {
            deleteAssetAssignmentByAssetId(assetId);
            assetService.updateAssetStatus(assetId, AssetStatus.MAINTENANCE);
            saveHistory(asset);
        } else if (asset.getStatus() == AssetStatus.MAINTENANCE) {
            throw new IllegalArgumentException("Asset is already under maintenance");
        } else {
            assetService.updateAssetStatus(assetId, AssetStatus.MAINTENANCE);
            saveHistory(asset);
        }
    }

    @Override
    public void completeAssetMaintenance(Long assetId) {
        Asset asset = assetService.findById(assetId);
        if (asset.getStatus() == AssetStatus.MAINTENANCE) {
            assetService.updateAssetStatus(assetId, AVAILABLE);

            History history = assetAssignmentHistoryRepository
                    .findByAssetAndEndDateIsNull(asset)
                    .orElseThrow(() -> new ResourceNotFound("No active history found"));

            history.setEndDate(LocalDateTime.now());
            assetAssignmentHistoryRepository.save(history);
        }
    }

    private void saveHistory(Asset asset) {
        LocalDateTime now = LocalDateTime.now();
        History history = new History();
        history.setUser(null);
        history.setAsset(asset);
        history.setNotes("Under maintenance");
        history.setStartDate(now);
        assetAssignmentHistoryRepository.save(history);
    }
}
