package com.example.testProject.controller.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpdateMgnRequest {
    private String mgniKacTyp;
    private String mgniPvType;
    private String mgniIType;
    private String mgniCtName;
    private String mgniCtTel;
    private String mgniStatus;
    private LocalDateTime mgniUTime;

}
