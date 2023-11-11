package com.example.apisimulacro202245678.repository;

import com.example.apisimulacro202245678.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //Usando Query Method (Keywords)
    boolean existsByName(String name);
    boolean existsByDni(String dni);
}
