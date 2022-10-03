package com.example.testProject.model.entriy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(PrimaryId.class)
@Table(name = "cashi")
public class MGN_CASHI {

    @Id
    @Column(name = "CASHI_MGNI_ID")
    private String cashiMgniId;

    @Id
    @Column(name = "CASHI_ACC_NO")
    private String cashiAccNo;

    @Id
    @Column(name = "CASHI_CCY")
    private String cashiCcy;


    @Column(name = "CASHI_AMT")
    private BigDecimal cashiAmt;

//    //try
//    @JoinColumn(name = "CASHI_MGNI_ID")
//    private MGN_CASHI CASHI;
}
