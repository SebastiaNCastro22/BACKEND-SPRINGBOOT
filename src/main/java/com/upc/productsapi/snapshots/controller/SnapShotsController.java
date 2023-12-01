package com.upc.productsapi.snapshots.controller;

import com.upc.productsapi.shared.dto.response.ApiResponse;
import com.upc.productsapi.snapshots.model.dto.request.SnapShotsRequestDto;
import com.upc.productsapi.snapshots.model.dto.response.SnapShotsResponseDto;
import com.upc.productsapi.snapshots.service.SnapshotsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SnapShots")
@RestController
@RequestMapping("/api/v1/products/{productId}/snapshots")
public class SnapShotsController {

    private final SnapshotsService snapshotService;

    @Autowired
    public SnapShotsController(SnapshotsService snapshotService) {
        this.snapshotService = snapshotService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SnapShotsResponseDto>> registerSnapshot(
            @PathVariable Long productId,
            @RequestBody SnapShotsRequestDto snapshotRequestDto) {

        ApiResponse<SnapShotsResponseDto> response = snapshotService.registerSnapshot(productId, snapshotRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
