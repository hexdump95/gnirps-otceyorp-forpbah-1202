package com.example.hospital.exceptions;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.hibernate.PersistentObjectException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage notFoundException(HttpServletRequest req, NotFoundException e) {
        return new ErrorMessage(404, e.getClass().getSimpleName(), req.getRequestURI(), e.getMessage());
    }

    @ExceptionHandler({
            PersistentObjectException.class,// detached entity passed to persist
            DataIntegrityViolationException.class, // Referential integrity constraint violation
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage badRequestException(HttpServletRequest req, Exception e) {
        return new ErrorMessage(400, e.getClass().getSimpleName(), req.getRequestURI(), e.getMessage());
    }

}
