package io.newschool.platform.u202121882.student.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequest {
    private String name;
    private Long dni;
    private String streetAddress;
    private String gender;
    private LocalDate birthdate;
    private String specialty;
}
