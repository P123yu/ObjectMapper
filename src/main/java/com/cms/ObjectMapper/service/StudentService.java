package com.cms.ObjectMapper.service;

import com.cms.ObjectMapper.dto.StudentDto;
import org.springframework.stereotype.Service;
import com.cms.ObjectMapper.entity.Student;

import java.util.List;
import java.util.Map;

@Service
public interface StudentService {

    // create
    Student createStudent(StudentDto studentDto);

    StudentDto getStudent(Long id);



}
