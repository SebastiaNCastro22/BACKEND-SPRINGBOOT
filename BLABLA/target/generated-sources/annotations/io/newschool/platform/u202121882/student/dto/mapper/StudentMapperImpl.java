package io.newschool.platform.u202121882.student.dto.mapper;

import io.newschool.platform.u202121882.student.dto.StudentRequest;
import io.newschool.platform.u202121882.student.dto.StudentResponse;
import io.newschool.platform.u202121882.student.model.Student;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-04T15:29:41-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student StudentRequestToStudent(StudentRequest studentRequest) {
        if ( studentRequest == null ) {
            return null;
        }

        Student student = new Student();

        student.setName( studentRequest.getName() );
        student.setDni( studentRequest.getDni() );
        student.setStreetAddress( studentRequest.getStreetAddress() );
        student.setGender( studentRequest.getGender() );
        student.setBirthdate( studentRequest.getBirthdate() );
        student.setSpecialty( studentRequest.getSpecialty() );

        return student;
    }

    @Override
    public StudentResponse StudentToStudentResponse(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setId( student.getId() );
        studentResponse.setName( student.getName() );
        studentResponse.setDni( student.getDni() );
        studentResponse.setStreetAddress( student.getStreetAddress() );
        studentResponse.setGender( student.getGender() );
        studentResponse.setBirthdate( student.getBirthdate() );
        studentResponse.setSpecialty( student.getSpecialty() );

        return studentResponse;
    }
}
