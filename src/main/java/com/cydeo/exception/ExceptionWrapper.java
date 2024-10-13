package com.cydeo.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ExceptionWrapper {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;

    public ExceptionWrapper(Integer status, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }


}
