package com.example.apisimulacro202245678.repository;

import com.example.apisimulacro202245678.model.Remuneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RemunerationRepository extends JpaRepository<Remuneration, Long> {

    //Usando @Query (JPQL)
    //Listado de remuneración por dni de empleado
    @Query("SELECT r FROM Remuneration r WHERE r.employee.dni=:dni")
    List<Remuneration> findRemunerationsByEmployeeDni(@Param("dni")String dni);

    //Listado de remuneración por rango de fechas
    @Query("SELECT r FROM Remuneration r WHERE r.createDate BETWEEN :startDate AND :endDate")
    List<Remuneration> findRemunerationsByCreateDateRange(@Param("startDate") LocalDate startDate,
                                                          @Param("endDate") LocalDate endDate);

}
