package com.example.Asset.Tracking.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HistoryDto {
    private Long historyId;
    private Long userId;
    private String userName;
    private Long assetId;
    private String assetName;
    private String assetSerialNumber;
    private String notes;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
