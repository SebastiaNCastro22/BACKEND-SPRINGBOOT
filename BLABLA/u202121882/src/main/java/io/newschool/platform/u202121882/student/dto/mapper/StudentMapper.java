package io.newschool.platform.u202121882.student.dto.mapper;

import io.newschool.platform.u202121882.student.dto.StudentRequest;
import io.newschool.platform.u202121882.student.dto.StudentResponse;
import io.newschool.platform.u202121882.student.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE  = Mappers.getMapper(StudentMapper.class);

    Student StudentRequestToStudent(StudentRequest studentRequest);

    StudentResponse StudentToStudentResponse(Student student);

}
