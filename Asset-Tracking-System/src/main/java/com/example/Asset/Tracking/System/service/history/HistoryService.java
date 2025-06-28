package com.example.Asset.Tracking.System.service.history;
import com.example.Asset.Tracking.System.dto.HistoryDto;
import com.example.Asset.Tracking.System.entity.History;
import com.example.Asset.Tracking.System.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService implements IHistoryService{

    private final HistoryRepository historyRepository;
    private final ModelMapper modelMapper;

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
    public HistoryDto covertToDto(History history){
        return new HistoryDto(
                history.getId(),
                history.getUser().getId(),
                history.getUser().getUserName(),
                history.getAsset().getId(),
                history.getAsset().getName(),
                history.getAsset().getSerialNumber(),
                history.getNotes(),
                history.getStartDate(),
                history.getEndDate()
        );
    }

    @Override
    public List<HistoryDto> convertAllToDto(List<History> historyList){
        return historyList.stream().map(this::covertToDto).toList();
    }


}
