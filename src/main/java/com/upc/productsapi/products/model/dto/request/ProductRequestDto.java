package com.upc.productsapi.products.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el objeto de petición para el registro de un producto
 * @author Jamutaq Ortega
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "La marca del producto no puede estar vacía")
    private String brand;

    @NotBlank(message = "El modelo del producto no puede estar vacío")
    private String model;

    @NotBlank(message = "El número de serie del producto no puede estar vacío")
    private String serialNumber;

    @Min(value = 1, message = "El nivel de monitoreo debe ser al menos 1 (Essential Monitoring)")
    private Integer monitoringLevel;


}
