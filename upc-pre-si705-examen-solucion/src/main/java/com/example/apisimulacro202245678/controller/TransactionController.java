package com.example.apisimulacro202245678.controller;


import com.example.apisimulacro202245678.exception.ResourceNotFoundException;
import com.example.apisimulacro202245678.exception.ValidationException;
import com.example.apisimulacro202245678.model.Account;
import com.example.apisimulacro202245678.model.Remuneration;
import com.example.apisimulacro202245678.model.Transaction;
import com.example.apisimulacro202245678.repository.AccountRepository;
import com.example.apisimulacro202245678.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/bank/v1")
public class TransactionController {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public TransactionController(AccountRepository accountRepository, TransactionRepository transactionRepository){
        this.accountRepository=accountRepository;
        this.transactionRepository=transactionRepository;
    }

    //Endopint (url): http://localhost:8080/api/bank/v1/transactions/filterByNameCustomer
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/transactions/filterByNameCustomer")
    public ResponseEntity<List<Transaction>> getAllTransactionsByNameCustomer(@RequestParam(value = "nameCustomer") String nameCustomer){
        return new ResponseEntity<List<Transaction>>(transactionRepository.findByNameCustomer(nameCustomer),HttpStatus.OK);

    }

    //Endopint (url): http://localhost:8080/api/bank/v1/transactions/filterByCreateDateRange
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/transactions/filterByCreateDateRange")
    public ResponseEntity<List<Transaction>> getAllTransactionByCreateRange(@RequestParam(value = "startDate") String startDate,
                                                                               @RequestParam(value = "endDate") String endDate){
       return new ResponseEntity<List<Transaction>>( transactionRepository.findTransactionByCreateDateRange(
                LocalDate.parse(startDate),LocalDate.parse(endDate)),HttpStatus.OK);

    }

    //Endopint (url): http://localhost:8080/api/bank/v1/accounts/1/transactions
    //Method: POST
    @Transactional
    @PostMapping("/accounts/{id}/transactions")
    public ResponseEntity<Transaction> createTransaction(@PathVariable(name = "id")Long accountId, @RequestBody Transaction transaction){
        Account account= accountRepository.findById(accountId)
                .orElseThrow(()->new ResourceNotFoundException("No se encuentra la cuenta bancaria con el id="+accountId));
        validateTransaction(transaction);
        transaction.setCreateDate(LocalDate.now());

        if(transaction.getType().equals("Retiro")){
            transaction.setBalance(transaction.getBalance()-transaction.getAmount());
        }

        if(transaction.getType().equals("Deposito")){
            transaction.setBalance(transaction.getAmount()+transaction.getBalance());
        }

        return new ResponseEntity<Transaction>(transactionRepository.save(transaction), HttpStatus.CREATED);
    }

    private void validateTransaction(Transaction transaction){
        String deposito = new String("Deposito");
        String retiro = new String("Retiro");

        if(transaction.getType()==null || transaction.getType().trim().isEmpty()){
            throw new ValidationException("El tipo de transacción bancaria debe ser obligatorio");
        }


        if(transaction.getAmount()<=0.0){
            throw new ValidationException("El monto en una transacción bancaria debe ser mayor de S/0.0");
        }

        if((transaction.getAmount()>transaction.getBalance())){
            throw new ValidationException("En una transacción bancaria tipo retiro el monto no puede ser mayor al saldo ");
        }

    }


}
