package io.newschool.platform.u202121882.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "dni", nullable = false)
    private Long dni;
    @Column(name = "street_address", nullable = false)
    private String streetAddress;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;
    @Column(name = "specialty", nullable = false)
    private String specialty;
}
