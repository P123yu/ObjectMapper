package com.cms.ObjectMapper.service.impl;

import com.cms.ObjectMapper.dto.StudentDto;
import com.cms.ObjectMapper.repository.StudentRepository;
import com.cms.ObjectMapper.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.ObjectMapper.entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Student createStudent(StudentDto studentDto) {
        System.out.println(studentDto);
        try {
            // Convert the student's quiz (Map<String, Object>) to a JSON string
            String quizJson = objectMapper.writeValueAsString(studentDto.getQuiz());

            System.out.println(quizJson+"quizJson");

            // Create a new Student entity for saving
            Student entityToSave = new Student();
            entityToSave.setName(studentDto.getName()); // Set other fields from the incoming student
            entityToSave.setCity(studentDto.getCity()); // Set other fields from the incoming student
            entityToSave.setQuiz(quizJson); // Set the JSON string to the quiz field

            return studentRepository.save(entityToSave);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save student with quiz JSON", e);
        }
    }

//    @Override
//    public Student getStudent(Long id) {
//        return studentRepository.findById(id).orElse(0L);
//    }


    @Override
    public StudentDto getStudent(Long id) {
        StudentDto studentDto=new StudentDto();
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        try {
            // Parse quiz JSON string to Map<String, List<String>>
            Map<String, List<String>> parsedQuiz = objectMapper.readValue(
                    student.getQuiz(), new TypeReference<Map<String, List<String>>>() {}
            );

            studentDto.setName(student.getName());
            studentDto.setCity(student.getCity());

            studentDto.setQuiz(parsedQuiz);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing quiz field", e);
        }

        return studentDto;
    }



}


