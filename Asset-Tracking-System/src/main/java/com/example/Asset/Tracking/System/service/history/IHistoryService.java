package com.example.Asset.Tracking.System.service.history;

import com.example.Asset.Tracking.System.dto.HistoryDto;
import com.example.Asset.Tracking.System.entity.History;

import java.util.List;

public interface IHistoryService {
    List<History> getHistoryByUserId(Long userId);

    List<History> getHistoryByAssetId(Long assetId);

    List<History> getHistoryByUserAndAsset(Long userId, Long assetId);

    HistoryDto covertToDto(History history);

    List<HistoryDto> convertAllToDto(List<History> historyList);
}
