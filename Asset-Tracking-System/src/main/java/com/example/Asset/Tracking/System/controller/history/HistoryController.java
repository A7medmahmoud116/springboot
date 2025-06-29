package com.example.Asset.Tracking.System.controller.history;

import com.example.Asset.Tracking.System.dto.HistoryDto;
import com.example.Asset.Tracking.System.entity.History;
import com.example.Asset.Tracking.System.response.ApiResponse;
import com.example.Asset.Tracking.System.service.history.IHistoryService;
import com.example.Asset.Tracking.System.util.report.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/history")
public class HistoryController {
    private final IHistoryService historyService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getHistoryByUser(@PathVariable Long userId) {
        List<History> histories = historyService.getHistoryByUserId(userId);
        List<HistoryDto> historyDtos = historyService.convertAllToDto(histories);
        return ResponseEntity.ok(
                new ApiResponse("success", historyDtos)
        );
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<ApiResponse> getHistoryByAsset(@PathVariable Long assetId) {
        List<History> histories = historyService.getHistoryByAssetId(assetId);
        List<HistoryDto> historyDtos = historyService.convertAllToDto(histories);
        return ResponseEntity.ok(
                new ApiResponse("success", historyDtos)
        );
    }

    @GetMapping("/user/{userId}/asset/{assetId}")
    public ResponseEntity<ApiResponse> getHistoryByUserAndAsset(@PathVariable Long userId,
                                                                @PathVariable Long assetId) {
        List<History> histories = historyService.getHistoryByUserAndAsset(userId, assetId);
        List<HistoryDto> historyDtos = historyService.convertAllToDto(histories);
        return ResponseEntity.ok(
                new ApiResponse("success", historyDtos)
        );
    }
    @GetMapping("/user/{userId}/pdf")
    public ResponseEntity<byte[]> getUserHistoryPdf(@PathVariable Long userId) {
        List<History> history = historyService.getHistoryByUserId(userId);
        List<HistoryDto> historyList= historyService.convertAllToDto(history);
        ByteArrayInputStream pdfStream = PdfGenerator.generateHistoryPdf(historyList);

        byte[] pdfBytes = pdfStream.readAllBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=asset_history_report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
    @GetMapping("asset/{assetId}/pdf")
    public ResponseEntity<byte[]> getAssetHistoryPdf(@PathVariable Long assetId) {
        List<History> history = historyService.getHistoryByAssetId(assetId);
        List<HistoryDto> historyList = historyService.convertAllToDto(history);
        ByteArrayInputStream pdfStream = PdfGenerator.generateHistoryPdf(historyList);

        byte[] pdfBytes = pdfStream.readAllBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=asset_" + assetId + "_history_report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
