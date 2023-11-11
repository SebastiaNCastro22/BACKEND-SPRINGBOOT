package io.newschool.platform.u202121882.student.service.impl;

import io.newschool.platform.u202121882.shared.exception.ValidationException;
import io.newschool.platform.u202121882.student.model.Student;
import io.newschool.platform.u202121882.student.repository.StudentRepository;
import io.newschool.platform.u202121882.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student createStudent(Student student) {
        existsStudentByDni(student);
        existsStudentByStreetAddress(student);
        validateStudent(student);
        return studentRepository.save(student);
    }

    private void validateStudent(Student student) {
        if (Period.between(student.getBirthdate(), LocalDate.now()).getYears() < 18){
            throw new ValidationException("the student must be of legal age (over 18)");
        }
        if (!student.getGender().equals("Male") && !student.getGender().equals("Female")){
            throw new ValidationException("the student gender must be Male or Female");
        }
    }

    private void existsStudentByStreetAddress(Student student) {
        if (studentRepository.existsStudentByStreetAddress(student.getStreetAddress())){
            throw new ValidationException("The student could not be registered because there is already one with the same street address");
        }
    }

    private void existsStudentByDni(Student student) {
        if (studentRepository.existsStudentByDni(student.getDni())){
            throw new ValidationException("The student could not be registered because there is already one with the same DNI");
        }
    }
}
