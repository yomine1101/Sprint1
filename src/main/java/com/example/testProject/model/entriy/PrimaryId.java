package com.example.testProject.model.entriy;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrimaryId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cashiMgniId;
    private String cashiAccNo;
    private String cashiCcy;
}
