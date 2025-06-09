package com.cms.ObjectMapper.controller;

import com.cms.ObjectMapper.dto.StudentDto;
import com.cms.ObjectMapper.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cms.ObjectMapper.entity.Student;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<Student>createStudent(@RequestBody StudentDto studentDto){

        System.out.println(studentDto+"studentDto111");
        Student studentObj=studentService.createStudent(studentDto);
        return studentObj !=null ? ResponseEntity.ok(studentObj) : ResponseEntity.badRequest().build();
    }



    @GetMapping("/get/{id}")
    public ResponseEntity<StudentDto>getStudent(@PathVariable Long id){
        StudentDto studentObj=studentService.getStudent(id);
        return studentObj !=null ? ResponseEntity.ok(studentObj) : ResponseEntity.notFound().build();
    }


}
