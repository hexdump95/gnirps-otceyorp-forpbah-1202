package com.example.hospital.exceptions;

public class ErrorMessage {
    private Integer code;
    private String errorType;
    private String url;
    private String message;

    public ErrorMessage(Integer code, String errorType, String url, String message) {
        this.code = code;
        this.errorType = errorType;
        this.url = url;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
