package com.cydeo.controller;

import com.cydeo.dto.ResponseDTO;
import com.cydeo.dto.TeacherDTO;
import com.cydeo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

         /*
           Endpoint: /api/v1/teacher

           JSON Response Body:
           <teachers data>
        */

    @GetMapping
    public List<TeacherDTO> getTeachers() {
        return teacherService.findAll();
    }


        /*
           Endpoint: /api/v1/teacher/{username}
           HTTP Status Code: 200

           JSON Response Body:
           "success": true
           "message": "Teacher is successfully retrieved."
           "code":200
           "data":<teacher data>
        */

    @GetMapping("/{username}")
    public ResponseEntity<ResponseDTO> getTeacher(@PathVariable String username) {
        ResponseDTO responseDTO = ResponseDTO
                .builder()
                .success(true)
                .code(200)
                .message("Teacher is successfully retrieved.")
                .data(teacherService.findByUsername(username))
                .build();

        return ResponseEntity.ok(responseDTO);

    }


       /*
           Endpoint: /api/v1/teacher
           HTTP Status Code: 201
           Custom Response Header: "teacherUsername", <created username>

           JSON Response Body:
           "success": true
           "message": "Teacher is successfully created."
           "code":201
           "data":<created teacher data>
     */

    @PostMapping
    public ResponseEntity<ResponseDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        ResponseDTO responseDTO = ResponseDTO
                .builder()
                .success(true)
                .code(201)
                .message("Teacher is successfully created.")
                .data(teacherService.createTeacher(teacherDTO))
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .header("teacherUsername", teacherDTO.getUsername())
                .body(responseDTO);


    }

}
