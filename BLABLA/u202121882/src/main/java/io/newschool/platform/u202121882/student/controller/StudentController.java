package io.newschool.platform.u202121882.student.controller;

import io.newschool.platform.u202121882.student.dto.StudentRequest;
import io.newschool.platform.u202121882.student.dto.StudentResponse;
import io.newschool.platform.u202121882.student.dto.mapper.StudentMapper;
import io.newschool.platform.u202121882.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Transactional
    @PostMapping("/students")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest){
        var student = StudentMapper.INSTANCE.StudentRequestToStudent(studentRequest);
        var studentCreated = studentService.createStudent(student);
        return new ResponseEntity<StudentResponse>(StudentMapper.INSTANCE.StudentToStudentResponse(studentCreated), HttpStatus.CREATED);
    }
}
