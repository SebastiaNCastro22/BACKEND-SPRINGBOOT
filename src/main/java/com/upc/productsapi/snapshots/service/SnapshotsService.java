package com.upc.productsapi.snapshots.service;

import com.upc.productsapi.products.model.dto.response.ProductResponseDto;
import com.upc.productsapi.products.model.entity.Product;
import com.upc.productsapi.products.repository.IProductRepository;
import com.upc.productsapi.shared.dto.enums.EStatus;
import com.upc.productsapi.shared.dto.response.ApiResponse;
import com.upc.productsapi.snapshots.model.dto.request.SnapShotsRequestDto;
import com.upc.productsapi.snapshots.model.dto.response.SnapShotsResponseDto;
import com.upc.productsapi.snapshots.model.entity.SnapShots;
import com.upc.productsapi.snapshots.repository.ISnapShotsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnapshotsService implements ISnapShotsService{


    private final ISnapShotsRepository snapShotsRepository;
    private final ModelMapper modelMapper;

    public SnapshotsService(ISnapShotsRepository snapShotsRepository, ModelMapper modelMapper) {
        this.snapShotsRepository = snapShotsRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ApiResponse<SnapShotsResponseDto> registerSnapshot(Long productId, SnapShotsRequestDto snapshotsData) {

        // DTO a Entity
        SnapShots snapShots = modelMapper.map(snapshotsData, SnapShots.class);
        snapShots.setProductId(productId);

        // Crear el snapshot
        SnapShots snapShotsSaved = snapShotsRepository.save(snapShots);

        // Entity a DTO
        SnapShotsResponseDto snapShotsResponse = modelMapper.map(snapShotsSaved, SnapShotsResponseDto.class);

        // Retornar la respuesta
        return new ApiResponse<>("Snapshot registrado correctamente", EStatus.SUCCESS, snapShotsResponse);
    }

    @Override
    public ApiResponse<Object> deleteSnapShotsById(Long id) {
        return null;
    }

    @Override
    public ApiResponse<SnapShotsResponseDto> getSnapshotsByProductSerialNumber(String productSerialNumber) {

        List<SnapShots> snapshots = snapShotsRepository.findByProductSerialNumber(productSerialNumber);

        List<SnapShotsResponseDto> snapshotsResponse = snapshots
                .stream()
                .map(snapshot -> modelMapper.map(snapshot, SnapShotsResponseDto.class))
                .toList();

        var snapShotsResponse = modelMapper.map(snapshots, SnapShotsResponseDto.class);

        return new ApiResponse<>("Snapshots consultados correctamente", EStatus.SUCCESS, snapShotsResponse);
    }
}
