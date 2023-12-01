package com.upc.productsapi.products.model.dto.response;

import com.upc.productsapi.products.model.dto.enums.MonitoringLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el objeto de respuesta para el registro de un producto
 * @author Jamutaq Ortega
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long productId;
    private String brand;
    private String model;
    private String serialNumber;
    private MonitoringLevel monitoringLevel;

}
