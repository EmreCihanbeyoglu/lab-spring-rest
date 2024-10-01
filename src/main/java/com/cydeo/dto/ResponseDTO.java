package com.cydeo.dto;

import lombok.*;

@Getter
@Builder
public class ResponseDTO {

    private boolean success;

    private String message;

    private int code;

    private Object data;

}
