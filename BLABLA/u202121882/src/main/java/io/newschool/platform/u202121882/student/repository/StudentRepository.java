package io.newschool.platform.u202121882.student.repository;

import io.newschool.platform.u202121882.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsStudentByDni(Long dni);
    boolean existsStudentByStreetAddress(String streetAddress);

}
