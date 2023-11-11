package com.example.apisimulacro202245678.repository;

import com.example.apisimulacro202245678.model.Book;
import com.example.apisimulacro202245678.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    boolean existsByCodeStudent(String codeStudent);
    boolean existsByCodeStudentAndBookAndBookLoan(String codeStudent, Book book, boolean bookLoan) ;
    List<Loan> findByCodeStudent(String codeStudent);
}
