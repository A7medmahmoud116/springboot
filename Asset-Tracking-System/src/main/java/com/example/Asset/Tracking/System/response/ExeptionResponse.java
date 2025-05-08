package com.example.Asset.Tracking.System.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExeptionResponse {
    private String message;
    private int status;
}
