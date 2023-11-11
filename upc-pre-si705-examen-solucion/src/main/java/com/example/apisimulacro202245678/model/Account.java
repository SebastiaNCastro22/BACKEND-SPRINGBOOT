package com.example.apisimulacro202245678.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_customer",length = 30, nullable = false)
    private String nameCustomer;
    @Column(name = "number_account",length = 13, nullable = false)
    private String numberAccount;


}
