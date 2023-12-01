package com.upc.productsapi.snapshots.model.dto.response;

import com.upc.productsapi.snapshots.model.dto.enums.Leakage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnapShotsResponseDto {

    private Long id;
    private Long productId;
    private String snapshot_id;
    private String productSerialNumber;
    private Double temperature;
    private Double energy;
    private Leakage leakage;

}
