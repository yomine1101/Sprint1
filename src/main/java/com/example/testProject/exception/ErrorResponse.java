package com.example.testProject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String error;
    private String message;

    private List<ErrorResponse.FieldError> fieldErrors = new ArrayList<>();

    private List<ErrorResponse.CheckError> checkErrors = new ArrayList<>();


    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class FieldError {
        private final String field;
        private final String message;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class CheckError {
        private final String message;
    }

    public void addFieldError(String field, String message) {
        fieldErrors.add(new ErrorResponse.FieldError(field, message));
    }

    public void addCheckError(String message) {
        checkErrors.add(new ErrorResponse.CheckError(message));
    }
}
