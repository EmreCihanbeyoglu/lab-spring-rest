package com.cydeo.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ValidationError {

    private String errorField;
    private Object rejectedValue;
    private String reason;

}
