package com.upc.productsapi.snapshots.model.entity;


import com.upc.productsapi.snapshots.model.dto.enums.Leakage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "snapshots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnapShots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String snapshot_id;

    @Column(nullable = false)
    private String productSerialNumber;

    @Column(nullable = false)
    private Double temperature;

    @Column(nullable = false)
    private Double energy;

    @Column(nullable = false)
    private Integer leakage;


    @Enumerated(EnumType.STRING)
    public Leakage getLeakageEnum() {
        if (leakage == null) {
            return null;
        }
        return leakage == 0 ? Leakage.NO_LEAKAGE : Leakage.LEAKAGE;
    }

    public void setLeakageEnum(Leakage leakageEnum) {
        this.leakage = leakageEnum == Leakage.NO_LEAKAGE ? 0 : 1;
    }

}
