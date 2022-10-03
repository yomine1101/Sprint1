package com.example.testProject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.constraints.Null;

@RestControllerAdvice
@Slf4j
public class DataNofFoundExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public Object exceptionHandler_1(DataNotFoundException e){
        String message = e.getMessage();
        String mag = "Message d'erreur:  " + message;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mag);
    }

    @ExceptionHandler(NullPointerException.class)
    public Object exceptionHandler_2(NullPointerException e){
        String mag = "Get Error: " + e;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mag);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {
        ErrorResponse errorResponse = new ErrorResponse(ActionError.VALIDATE.getMsg(), "");
        System.out.println("gggg");
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addFieldError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }


}
