package com.example.apisimulacro202245678.controller;

import com.example.apisimulacro202245678.exception.ValidationException;
import com.example.apisimulacro202245678.model.Account;
import com.example.apisimulacro202245678.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank/v1")
public class AccountController {
    private final AccountRepository accountRepository;
    public AccountController(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    //Endopint (url): http://localhost:8080/api/bank/v1/accounts
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<List<Account>>(accountRepository.findAll(), HttpStatus.OK);
    }

    //Endopint (url): http://localhost:8080/api/v1/bank/accounts
    //Method: POST
    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        existsAccountByNameCustomerAndNumberAccount(account);
        validateAccount(account);
        return new ResponseEntity<Account>(accountRepository.save(account), HttpStatus.CREATED);
    }

    private void validateAccount(Account account){
        if(account.getNameCustomer()==null || account.getNameCustomer().trim().isEmpty()){
            throw new ValidationException("El nombre del cliente debe ser obligatorio");
        }

        if(account.getNumberAccount()==null || account.getNumberAccount().trim().isEmpty()){
            throw new ValidationException("El número de cuenta debe ser obligatorio");
        }

        if(account.getNameCustomer().length()>30){
            throw new ValidationException("El nombre de cliente no debe exceder los 30 caracteres");
        }

        if(account.getNumberAccount().length() != 13){
            throw new ValidationException("El número de cuenta debe tener una longitud de 13 caracteres");
        }


    }

    private void existsAccountByNameCustomerAndNumberAccount(Account account){
        if(accountRepository.existsByNameCustomerAndNumberAccount(account.getNameCustomer(), account.getNumberAccount())){
            throw new ValidationException("No se puede registrar la cuenta porque ya existe uno con estos datos");
        }
    }
}
