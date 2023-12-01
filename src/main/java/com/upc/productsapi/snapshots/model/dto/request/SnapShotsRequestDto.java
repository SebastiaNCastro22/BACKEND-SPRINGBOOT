package com.upc.productsapi.snapshots.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnapShotsRequestDto {
    @NotBlank(message = "El snapshotId no puede estar vacío")
    private Long productId;

    @NotBlank(message = "El snapshotId no puede estar vacío")
    private String snapshot_id;

    @NotBlank(message = "El productSerialNumber no puede estar vacío")
    private String productSerialNumber;

    @NotNull(message = "La temperatura no puede ser nula")
    private Double temperature;

    @NotNull(message = "La energía no puede ser nula")
    @Min(value = 0, message = "La energía debe ser mayor o igual a cero")
    private Double energy;

    @NotNull(message = "La fuga no puede ser nula")
    @Min(value = 0, message = "La fuga debe ser 0 (No Leakage) o 1 (Leakage)")
    private Integer leakage;
}
