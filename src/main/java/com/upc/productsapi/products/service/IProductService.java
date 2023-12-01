package com.upc.productsapi.products.service;

import com.upc.productsapi.products.model.dto.request.ProductRequestDto;
import com.upc.productsapi.products.model.dto.response.ProductResponseDto;
import com.upc.productsapi.shared.dto.response.ApiResponse;

import java.util.List;


public interface IProductService {
    /**
     * Método que se encarga de registrar un producto
     * @param productData datos del producto a registrar
     * @return ApiResponse con los datos del producto registrado
     */
    ApiResponse<ProductResponseDto> registerProduct(ProductRequestDto productData);

    /**
     * Método que se encarga de eliminar un producto por su id
     */
    ApiResponse<Object> deleteProductById(Long productId);

    /**
     * Método que se encarga de obtener un producto por su nombre
     * @return ApiResponse con los datos del producto
     */
    ApiResponse<ProductResponseDto> getProductByProductId(long id);

    /**
     * Método que se encarga de obtener todos los productos
     * @return ApiResponse con la lista de productos
     */
    ApiResponse<List<ProductResponseDto>> getAllProducts();
}
