package com.waes.techassigment.datadiff.controller;

import com.waes.techassigment.datadiff.exceptions.IncorrectDataSetupException;
import com.waes.techassigment.datadiff.exceptions.NoDiffDataFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ IncorrectDataSetupException.class })
    protected ResponseEntity<Object> handlePrecondition(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }

    @ExceptionHandler({ NoDiffDataFoundException.class })
    protected ResponseEntity<Object> handleNotFound(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    protected ResponseEntity<Object> handleInvalidData(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Body data not valid or incorrect enconding.",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ NoSuchElementException.class })
    protected ResponseEntity<Object> handleNoSuchElement(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Body data not present.",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
