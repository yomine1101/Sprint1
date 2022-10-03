package com.example.testProject.controller.dto.response;

import com.example.testProject.model.entriy.MGN_CASHI;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class StatusResponse {

    private String mgniId;
    private LocalDateTime mgniTime;
    private String mgniType;
    private String mgniCmNo;
    private String mgniKacTyp;
    private String mgniBankNo;
    private String mgniCcy;
    private String mgniPvType;
    private String mgniBicaccN0;
    private String mgniIType;
    private String  mgniPReasonp;
    private String mgniAmt;

    private List<MGN_CASHI> cashiList;

    private String mgniCtName;
    private String mgniCtTel;
    private String mgniStatus;
    private LocalDateTime mgniUTime;

    private String message;



}
