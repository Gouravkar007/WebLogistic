package com.parthi.logistic.common.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.parthi.logistic.common.model.ErrorResponse;
import com.parthi.logistic.common.model.ValidationErrorResponse;

import org.springframework.lang.Nullable;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<Object> HandleException(InternalException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> HandleException(AlreadyExistsException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> HandleException(BadRequestException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    
    @Override
    @Nullable
    @SuppressWarnings("null")
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errList = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errList.add(error.getDefaultMessage());
        }

        return new ResponseEntity<>(new ValidationErrorResponse(errList), HttpStatus.BAD_REQUEST);
    }

}
