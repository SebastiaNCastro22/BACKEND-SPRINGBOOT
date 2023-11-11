package com.example.apisimulacro202245678.controller;

import com.example.apisimulacro202245678.exception.ResourceNotFoundException;
import com.example.apisimulacro202245678.exception.ValidationException;
import com.example.apisimulacro202245678.model.Book;
import com.example.apisimulacro202245678.model.Loan;
import com.example.apisimulacro202245678.repository.BookRepository;
import com.example.apisimulacro202245678.repository.LoanRepository;
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
        this.loanRepository=loanRepository;
        this.bookRepository=bookRepository;
    }

    //Endopint (url): http://localhost:8080/api/v1/library/loans/filterByCodeStudent
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/loans/filterByCodeStudent")
    public ResponseEntity<List<Loan>> getAllLoansByCodeStudent(@RequestParam(value = "codeStudent") String codeStudent){
        return new ResponseEntity<List<Loan>>(loanRepository.findByCodeStudent(codeStudent),HttpStatus.OK);

    }

    //Endopint (url): http://localhost:8080/api/v1/library/books/1/loans
    //Method: POST
    @Transactional
    @PostMapping("/books/{id}/loans")
    public ResponseEntity<Loan> createLoan(@PathVariable(name = "id")Long bookId, @RequestBody Loan loan){
        Book book= bookRepository.findById(bookId)
                        .orElseThrow(()->new ResourceNotFoundException("No se encuentra el libro con el id="+bookId));
        existsLoanByCodeStudentAndBookAndDevolution(loan,book);
        validateLoan(loan);
        loan.setLoanDate(LocalDate.now());
        loan.setDevolutionDate(LocalDate.now().plusDays(3));
        loan.setBookLoan(true);
        //loan.setBook(book);
        return new ResponseEntity<Loan>(loanRepository.save(loan), HttpStatus.CREATED);
    }


    private void validateLoan(Loan loan){
        if(loan.getCodeStudent()==null || loan.getCodeStudent().trim().isEmpty()){
            throw new ValidationException("El código del alumno debe ser obligatorio");
        }

        if(loan.getCodeStudent().length()<10){
            throw new ValidationException("El código del alumno debe tener 10 caracteres");
        }
    }

    private void existsLoanByCodeStudentAndBookAndDevolution(Loan loan, Book book){
        if(loanRepository.existsByCodeStudentAndBookAndBookLoan(loan.getCodeStudent(),book,true)){
            throw new ValidationException("El prestamo del libro "+book.getTitle()+" no es posible porque ya fue prestado por el alumno "+loan.getCodeStudent());
        }
    }
}
