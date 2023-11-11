package com.upc.ejerc.Ejercicio.controller;

import com.upc.ejerc.Ejercicio.exception.ResourceNotFoundException;
import com.upc.ejerc.Ejercicio.exception.ValidationException;
import com.upc.ejerc.Ejercicio.model.Book;
import com.upc.ejerc.Ejercicio.model.Loan;
import com.upc.ejerc.Ejercicio.repository.BookRepository;
import com.upc.ejerc.Ejercicio.repository.LoanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/library/v1")
public class LoanController {

    private LoanRepository loanRepository;
    private BookRepository bookRepository;

    public LoanController(LoanRepository loanRepository, BookRepository bookRepository){
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    //FILTRAR POR CODIGO DE ESTUDIANTE
    //URL: http://localhost:8080/api/library/v1/loans/filterByCodeStudent
    //Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/loans/filterByCodeStudent")
    public ResponseEntity<List<Loan>> getAllLoansByCodeStudent(@RequestParam(name = "codeStudent") String codeStudent){
        return new ResponseEntity<List<Loan>>(loanRepository.findByCodeStudent(codeStudent), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/library/v1/books/{id}/loans
    //Method: POST

    @Transactional
    @PostMapping("/books/{id}/loans")
    public ResponseEntity<Loan> createLoan(@PathVariable(value = "id") long bookId, @RequestBody Loan loan){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("No se encuentra el libro"));


        loan.setLoanDate(LocalDate.now());
        loan.setDevolutionDate(LocalDate.now().plusDays(3));
        loan.setBookLoan(true);
        //loan.setBook(book);
        return new ResponseEntity<Loan>(loanRepository.save(loan), HttpStatus.CREATED);
    }

    private void validateLoan(Loan loan){
        if (loan.getCodeStudent() == null || loan.getCodeStudent().trim().isEmpty()){
            throw new ValidationException("El codigo del alumno debe ser obligatorio");
        }

        if (loan.getCodeStudent().length()<10){
            throw new ValidationException("El codigo del alumno debe tener 10 caracteres");
        }

    }

    private void existLoanByCodeStudentAndBookAndDevolution(Loan loan, Book book){
        if (loanRepository.existsByCodeStudentAndBookAndBookLoan(loan.getCodeStudent(), book, false)){
            throw new ValidationException("El prestamo del libro + " + book.getTitle() + "no es posible porque ya fue " +
                    "prestado por el alumno con codigo"+loan.getCodeStudent());

        }
    }

}
