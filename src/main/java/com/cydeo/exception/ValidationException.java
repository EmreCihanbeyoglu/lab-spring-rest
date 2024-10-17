package com.cydeo.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ValidationException {

    private String errorField;
    private Object rejectedValue;
    private String reason;

}
