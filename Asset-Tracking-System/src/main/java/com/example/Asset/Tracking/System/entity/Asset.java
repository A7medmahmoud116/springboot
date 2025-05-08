package com.example.Asset.Tracking.System.entity;

import com.example.Asset.Tracking.System.enums.AssetStatus;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;
}
