package com.cydeo.controller;

import com.cydeo.dto.ResponseDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

     /*
           Endpoint: /api/v1/student
           HTTP Status Code: 200

           JSON Response Body:
           "success": true
           "message": "Students are successfully retrieved."
           "code":200
           "data":<students data>
     */

    @GetMapping
    public ResponseEntity<ResponseDTO> getStudents() {
        ResponseDTO responseDTO = ResponseDTO
                .builder()
                .success(true)
                .message("Students are successfully retrieved.")
                .code(200)
                .data(studentService.findAll())
                .build();

        return ResponseEntity.ok(responseDTO);
    }


    /*
          Endpoint: /api/v1/student
          HTTP Status Code: 201

          JSON Response Body:
          "success": true
          "message": "Student is successfully created."
          "code":201
          "data":<created student data>
    */

    @PostMapping
    public ResponseEntity<ResponseDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        ResponseDTO responseDTO = ResponseDTO
                .builder()
                .success(true)
                .message("Student added successfully.")
                .code(201)
                .data(studentService.createStudent(studentDTO))
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(responseDTO);
    }


}
