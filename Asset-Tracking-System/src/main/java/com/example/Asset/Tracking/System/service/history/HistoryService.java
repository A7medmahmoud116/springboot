package com.example.Asset.Tracking.System.service.history;
import com.example.Asset.Tracking.System.dto.HistoryDto;
import com.example.Asset.Tracking.System.entity.History;
import com.example.Asset.Tracking.System.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService implements IHistoryService{

    private final HistoryRepository historyRepository;


    @Override
    public List<History> getHistoryByUserId(Long userId) {
        return historyRepository.findByUserId(userId);
    }

    @Override
    public List<History> getHistoryByAssetId(Long assetId) {
        return historyRepository.findByAssetId(assetId);
    }

    @Override
    public List<History> getHistoryByUserAndAsset(Long userId, Long assetId) {
        return historyRepository.findByUserIdAndAssetId(userId, assetId);
    }

    @Override
    public HistoryDto covertToDto(History history) {
        HistoryDto dto = new HistoryDto();
        dto.setHistoryId(history.getId());
        dto.setNotes(history.getNotes());
        dto.setStartDate(history.getStartDate());
        dto.setEndDate(history.getEndDate());

        if (history.getUser() != null) {
            dto.setUserId(history.getUser().getId());
            dto.setUserName(history.getUser().getUsername());
        }

        if (history.getAsset() != null) {
            dto.setAssetId(history.getAsset().getId());
            dto.setAssetName(history.getAsset().getName());
            dto.setAssetSerialNumber(history.getAsset().getSerialNumber());
        }

        return dto;
    }

    @Override
    public List<HistoryDto> convertAllToDto(List<History> historyList){
        return historyList.stream().map(this::covertToDto).toList();
    }


}
