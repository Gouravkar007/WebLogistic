package com.parthi.logistic.customer.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tools.jackson.databind.exc.InvalidFormatException;

/**
 * This class handles the Global exception like DOB
 * @return Returns the response entitiy.bad request 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException ex) {

        if (ex.getCause() instanceof InvalidFormatException invalidFormat) {
            if (invalidFormat.getTargetType().equals(LocalDate.class)) {
            return ResponseEntity.badRequest().body("DOB must be in format yyyy-MM-dd and cannot contain letters");
            }
        }

        return ResponseEntity.badRequest().body("Invalid request");
    }
}