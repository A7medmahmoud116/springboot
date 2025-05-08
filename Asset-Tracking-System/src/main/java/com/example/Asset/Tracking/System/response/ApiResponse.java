package com.example.Asset.Tracking.System.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;
}
