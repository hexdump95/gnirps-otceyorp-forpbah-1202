package com.example.hospital.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessage {
    private Integer code;
    private String errorType;
    private String url;
    private String message;
}
