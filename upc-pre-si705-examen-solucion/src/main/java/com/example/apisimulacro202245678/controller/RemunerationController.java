package com.example.apisimulacro202245678.controller;

import com.example.apisimulacro202245678.exception.ResourceNotFoundException;
import com.example.apisimulacro202245678.exception.ValidationException;
import com.example.apisimulacro202245678.model.Employee;
import com.example.apisimulacro202245678.model.Remuneration;
import com.example.apisimulacro202245678.repository.EmployeeRepository;
import com.example.apisimulacro202245678.repository.RemunerationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RemunerationController {

    private final RemunerationRepository remunerationRepository;
    private final EmployeeRepository employeeRepository;

    public RemunerationController(RemunerationRepository remunerationRepository, EmployeeRepository employeeRepository){
        this.remunerationRepository=remunerationRepository;
        this.employeeRepository=employeeRepository;
    }

    //Endopint (url): http://localhost:8080/api/v1/employees/{id}/remunerations
    //Method: POST
    @Transactional
    @PostMapping("/employees/{id}/remunerations")
    public ResponseEntity<Remuneration> createRemuneration(@PathVariable(value="id") Long employeeId,
                                                           @RequestBody Remuneration remuneration){
        validateRemuneration(remuneration);
        remuneration.setCreateDate(LocalDate.now());
        remuneration.setSalary(calculateSalary(employeeId));
        return new ResponseEntity<Remuneration>(remunerationRepository.save(remuneration), HttpStatus.CREATED);
    }

    //Endopint (url): http://localhost:8080/api/v1/remunerations/filterByEmployeeDni
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/remunerations/filterByEmployeeDni")
    public ResponseEntity<List<Remuneration>> getAllRemunerationsByEmployeeDni(@RequestParam(value = "dni") String dni){
        List<Remuneration> remunerations;
        if(employeeRepository.existsByDni(dni))
            remunerations=remunerationRepository.findRemunerationsByEmployeeDni(dni);
        else
            throw new ResourceNotFoundException("No existe un empleado con el dni="+dni);

        return new ResponseEntity<List<Remuneration>>(remunerations,HttpStatus.OK);

    }


    //Endopint (url): http://localhost:8080/api/v1/remunerations/filterByCreateDateRange
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/remunerations/filterByCreateDateRange")
    public ResponseEntity<List<Remuneration>> getAllRemunerationsByCreateRange(@RequestParam(value = "startDate") String startDate,
                                                                               @RequestParam(value = "endDate") String endDate){
        List<Remuneration> remunerations=remunerationRepository.findRemunerationsByCreateDateRange(
          LocalDate.parse(startDate),LocalDate.parse(endDate));

        return new ResponseEntity<List<Remuneration>>(remunerations,HttpStatus.OK);

    }

    //Reglas de negocio
    //Validaciones
    private void validateRemuneration(Remuneration remuneration){
        if(remuneration.getContent()==null || remuneration.getContent().trim().isEmpty()){
            throw new ValidationException("El comentario de la remuneración es obligatorio");
        }

        if(remuneration.getContent().length()<4 || remuneration.getContent().length()>255){
            throw new ValidationException("El comentario debe tener mínimo 4 y máximo 255 caracteres");
        }
    }

    private double calculateSalary(Long employeeId){
        double salary;
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("No existe un empleado con el id="+employeeId));

        if(employee.getHorasTrabajadas()<=30)
            salary=employee.getHorasTrabajadas()*60;
        else
            salary=30*60+ 30*employee.getHorasExtras();

        return salary;
    }
}
