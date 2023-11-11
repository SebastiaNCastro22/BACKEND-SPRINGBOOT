package com.example.apisimulacro202245678.repository;

import com.example.apisimulacro202245678.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByNameCustomerAndNumberAccount(String nameCustomer, String  numberAccount);
}
