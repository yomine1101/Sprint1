package com.example.testProject.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ParamInvalidException extends Exception{
    private List<String> errMessages;

    public ParamInvalidException(List<String> errMessages) {
        this.errMessages = errMessages;
    }
}
