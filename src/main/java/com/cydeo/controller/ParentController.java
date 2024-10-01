package com.cydeo.controller;

import com.cydeo.dto.ParentDTO;
import com.cydeo.dto.ResponseDTO;
import com.cydeo.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parent")
public class ParentController {
    private final ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

     /*
           Endpoint: /api/v1/parent
           HTTP Status Code: 200
           Custom Response Header: "Parents", "Returned"

           JSON Response Body:
           "success": true
           "message": "Parents are successfully retrieved."
           "code":200
           "data":<parents data>
     */
    @GetMapping
    public ResponseEntity<ResponseDTO> getParent() {
        ResponseDTO responseDTO = ResponseDTO
                .builder()
                .success(true)
                .message("Parents are successfully retrieved")
                .code(200)
                .data(parentService.findAll())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .header("Parents", "Returned")
                .body(responseDTO);

    }

}
