package com.example.testProject.controller.dto.request;

import com.example.testProject.model.entriy.MGN_CASHI;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMgniRequest {
    private String mgniId;
    private LocalDateTime mgniTime;

    @NotBlank(message = "不可為空值")
    private String mgniType;
    @NotBlank(message = "不可為空值")
    private String mgniCmNo;

    @NotBlank(message = "不可為空值")
//    @Pattern(regexp = "^[1-2]$", message = "請輸入 1-2")
    private String mgniKacTyp;

    @NotBlank(message = "不可為空值")
    private String mgniBankNo;

    @NotBlank(message = "不可為空值")
    private String mgniCcy;

    @NotBlank(message = "不可為空值")
    private String mgniPvType;

    @NotBlank(message = "不可為空值")
    private String mgniBicaccN0;

    @NotBlank(message = "不可為空值")
    private String mgniIType;

    @NotBlank(message = "不可為空值")
    private String  mgniPReasonp;


    private BigDecimal mgniAmt;
    @NotBlank(message = "不可為空值")
    private String mgniCtName;
    @NotBlank(message = "不可為空值")
    private String mgniCtTel;
    @NotBlank(message = "不可為空值")
    private String mgniStatus;

    private LocalDateTime mgniUTime;

    private List<MGN_CASHI> cashiList;
}
