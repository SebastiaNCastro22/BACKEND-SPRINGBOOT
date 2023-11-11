package com.upc.ejerc.Ejercicio.repository;

import com.upc.ejerc.Ejercicio.model.Book;
import com.upc.ejerc.Ejercicio.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LoanRepository extends JpaRepository<Loan, Long> {

    boolean existsByCodeStudent(String codStudent);

    boolean existsByCodeStudentAndBookAndBookLoan(String codeStudent, Book book, boolean bookLoan);

    List<Loan> findByCodeStudent(String codeStudent);



}
