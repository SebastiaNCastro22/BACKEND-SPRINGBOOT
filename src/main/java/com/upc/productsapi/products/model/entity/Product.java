package com.upc.productsapi.products.model.entity;

import com.upc.productsapi.products.model.dto.enums.MonitoringLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa la tabla products
 * @author Jamutaq Ortega
 */
@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private Integer monitoringLevel;

    @Enumerated(EnumType.STRING)
    public MonitoringLevel getMonitoringLevelEnum() {
        if (monitoringLevel == null) {
            return null;
        }
        return monitoringLevel == 1 ? MonitoringLevel.ESSENTIAL_MONITORING : MonitoringLevel.ADVANCE_MONITORING;
    }

    public void setMonitoringLevelEnum(MonitoringLevel monitoringLevelEnum) {
        this.monitoringLevel = monitoringLevelEnum == MonitoringLevel.ESSENTIAL_MONITORING ? 1 : 2;
    }

}
