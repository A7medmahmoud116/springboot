package com.example.Asset.Tracking.System.entity;

import com.example.Asset.Tracking.System.enums.AssetStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    private List<AssetAssignment> assignments;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    private List<History> histories;
}
