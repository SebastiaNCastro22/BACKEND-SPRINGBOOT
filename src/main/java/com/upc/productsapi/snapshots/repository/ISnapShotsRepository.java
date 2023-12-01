package com.upc.productsapi.snapshots.repository;

import com.upc.productsapi.snapshots.model.entity.SnapShots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISnapShotsRepository extends JpaRepository<SnapShots, Long> {

    List<SnapShots> findByProductSerialNumber(String productSerialNumber);

}
