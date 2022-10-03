package com.example.testProject.model.entriy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mgni")
public class MGN_MGNI  {


    @Id
    @Column(name = "MGNI_ID") /* MGI + yyyymmdd + hhmmssfff */
    private String mgniId;

    @Column(name = "MGNI_TIME")
    private LocalDateTime mgniTime;

    @Column(name = "MGNI_TYPE")
    private String mgniType;

    @Column(name = "MGNI_CM_NO")
    private String mgniCmNo;

    @Column(name = "MGNI_KAC_TYPE")
    private String mgniKacTyp;

    @Column(name = "MGNI_BANK_NO")
    private String mgniBankNo;

    @Column(name = "MGNI_CCY")
    private String mgniCcy;

    @Column(name = "MGNI_PV_TYPE")
    private String mgniPvType;

    @Column(name = "MGNI_BICACC_NO")
    private String mgniBicaccN0;

    @Column(name = "MGNI_I_TYPE")
    private String mgniIType;

    @Column(name = "MGNI_P_REASON")
    private String  mgniPReasonp;

    @Column(name = "MGNI_AMT")
    private BigDecimal mgniAmt;

    @Column(name = "MGNI_CT_NAME")
    private String mgniCtName;

    @Column(name = "MGNI_CT_TEL")
    private String mgniCtTel;

    @Column(name = "MGNI_STATUS")
    private  String mgniStatus;

    @Column(name = "MGNI_U_TIME")
    private LocalDateTime mgniUTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CASHI_MGNI_ID")
    private List<MGN_CASHI> mgn_cashis;
}


