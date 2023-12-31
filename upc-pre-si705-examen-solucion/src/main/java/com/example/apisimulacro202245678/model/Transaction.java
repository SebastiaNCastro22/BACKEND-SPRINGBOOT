package com.example.apisimulacro202245678.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transactions")
public class Transaction  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="type", length = 15,nullable = false)
    private String type;
    @Column(name="create_date", nullable = false)
    private LocalDate createDate;
    @Column(name="amount", nullable = false)
    private Double amount;
    @Column(name = "balance", nullable = false)
    private Double balance;
    @ManyToOne
    @JoinColumn(name="account_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ACCOUNT_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Account account;
}
