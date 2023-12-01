package com.upc.productsapi.products.repository;

import com.upc.productsapi.products.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductId(long productId);
}
