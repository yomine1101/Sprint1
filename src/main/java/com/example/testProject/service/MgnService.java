package com.example.testProject.service;

import com.example.testProject.controller.dto.request.CreateMgniRequest;
import com.example.testProject.controller.dto.request.UpdateMgnRequest;
import com.example.testProject.controller.dto.response.StatusResponse;
import com.example.testProject.exception.DataNotFoundException;
import com.example.testProject.exception.ParamInvalidException;
import com.example.testProject.model.CashiRepository;
import com.example.testProject.model.MgnRepository;
import com.example.testProject.model.entriy.MGN_CASHI;
import com.example.testProject.model.entriy.MGN_MGNI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MgnService {

    @Autowired
    MgnRepository mgnRepository;

    @Autowired
    CashiRepository cashiRepository;

    public StatusResponse getAllId(@PathVariable String mgniId) throws Exception{
        StatusResponse statusResponse = new StatusResponse();
        MGN_MGNI mgn_mgni = this.mgnRepository.findByMgniId(mgniId);
        List<MGN_CASHI> cashiList = new ArrayList<>();
        try {
            if(mgn_mgni.getMgniId().isEmpty()){
                throw new DataNotFoundException("查無資料");
            }
            statusResponse.setMgniId(mgn_mgni.getMgniId());
            statusResponse.setMgniTime(mgn_mgni.getMgniTime());
            statusResponse.setMgniType(mgn_mgni.getMgniType());
            statusResponse.setMgniCmNo(mgn_mgni.getMgniCmNo());
            statusResponse.setMgniKacTyp(mgn_mgni.getMgniKacTyp());
            statusResponse.setMgniBankNo(mgn_mgni.getMgniBankNo());
            statusResponse.setMgniCcy(mgn_mgni.getMgniCcy());
            statusResponse.setMgniPvType(mgn_mgni.getMgniPvType());
            statusResponse.setMgniBicaccN0(mgn_mgni.getMgniBicaccN0());
            statusResponse.setMgniIType(mgn_mgni.getMgniIType());
            statusResponse.setMgniPReasonp(mgn_mgni.getMgniPReasonp());
            statusResponse.setMgniAmt(mgn_mgni.getMgniAmt().toString());

            for(MGN_CASHI i: mgn_mgni.getMgn_cashis()){
                MGN_CASHI mgn_cashi = new MGN_CASHI();
                mgn_cashi.setCashiMgniId(i.getCashiMgniId());
                mgn_cashi.setCashiAccNo(i.getCashiAccNo());
                mgn_cashi.setCashiCcy(i.getCashiCcy());
                mgn_cashi.setCashiAmt(i.getCashiAmt());
                cashiList.add(mgn_cashi);
            }

            statusResponse.setMgniCtName(mgn_mgni.getMgniCtName());
            statusResponse.setMgniCtTel(mgn_mgni.getMgniCtTel());
            statusResponse.setMgniStatus(mgn_mgni.getMgniStatus());
            statusResponse.setMgniUTime(mgn_mgni.getMgniUTime());

            statusResponse.setCashiList(cashiList);

            return statusResponse;

        }catch (Exception e){
            if(e instanceof DataNotFoundException){
                throw e;
            }
            throw new Exception();
        }
    }

//    Transactional rollback
    @Transactional(rollbackFor=RuntimeException.class)
    public CreateMgniRequest createMGNI(CreateMgniRequest request) throws Exception{
        try {
            MGN_MGNI checkId = this.mgnRepository.findByMgniId(request.getMgniId());

            if (checkId == null) {
                String time = DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS").format(LocalDateTime.now());
                MGN_MGNI mgn_mgni = new MGN_MGNI();
                BigDecimal temp = new BigDecimal(0);

                mgn_mgni.setMgniId("MGI" + time);
                mgn_mgni.setMgniTime(LocalDateTime.now());
                mgn_mgni.setMgniType(request.getMgniType());
                mgn_mgni.setMgniCmNo(request.getMgniCmNo());
                mgn_mgni.setMgniKacTyp(request.getMgniKacTyp());
                mgn_mgni.setMgniBankNo(request.getMgniBankNo());
                mgn_mgni.setMgniCcy(request.getMgniCcy());
                mgn_mgni.setMgniPvType(request.getMgniPvType());
                mgn_mgni.setMgniBicaccN0(request.getMgniBicaccN0());
                mgn_mgni.setMgniIType(request.getMgniIType());
                mgn_mgni.setMgniPReasonp(request.getMgniPReasonp());


                /* cash */
                switch (request.getMgniKacTyp()) {
                    case "1":
                        for (MGN_CASHI cashi : request.getCashiList()) {
                            temp = temp.add(cashi.getCashiAmt());
                        }
                        mgn_mgni.setMgniAmt(temp);
                        break;

                    case "2":
                        mgn_mgni.setMgniAmt(request.getMgniAmt());
                        break;
                    default:

                        throw new DataNotFoundException("保管專戶別請輸入1(結算保證金) or 2(交割結算基金專戶)");
                }


                mgn_mgni.setMgniCtName(request.getMgniCtName());
                mgn_mgni.setMgniCtTel(request.getMgniCtTel());
                mgn_mgni.setMgniStatus(request.getMgniStatus());
                mgn_mgni.setMgniUTime(LocalDateTime.now());
                mgnRepository.save(mgn_mgni);

                List<MGN_CASHI> cashiList = new ArrayList<>();

                switch (request.getMgniKacTyp()){
                    case "1":
                        for (MGN_CASHI cashi : request.getCashiList()) {
                            MGN_CASHI mgn_cashi = new MGN_CASHI();

                            mgn_cashi.setCashiMgniId(mgn_mgni.getMgniId());
                            mgn_cashi.setCashiAccNo(cashi.getCashiAccNo());
                            mgn_cashi.setCashiCcy(mgn_mgni.getMgniCcy());
                            mgn_cashi.setCashiAmt(cashi.getCashiAmt());
                            cashiList.add(mgn_cashi);
                            cashiRepository.save(mgn_cashi);
                        }
                        break;
                    case "2":
                        break;
                }
                return request;
            }
            List<String> message = new ArrayList<>();
            message.add("資料已存在");
            throw new ParamInvalidException(message);

        } catch (Exception e) {
            if (e instanceof ParamInvalidException) {
                throw e;
            }
            if (e instanceof DataNotFoundException) {
                throw e;
            }
            throw new Exception();
        }
    }

    /* update */
    public String update(String mgniId, UpdateMgnRequest request) throws Exception {
        try {
            MGN_MGNI mgn_mgni = mgnRepository.findByMgniId(mgniId);
            if (mgn_mgni != null) {
                /* 存入保管專戶別 */
                mgn_mgni.setMgniKacTyp(request.getMgniKacTyp());
                /* 存入方式 */
                mgn_mgni.setMgniPvType(request.getMgniPvType());
                /* 存入類別 */
                mgn_mgni.setMgniIType(request.getMgniIType());
                /* 聯絡人姓名、電話 */
                mgn_mgni.setMgniCtName(request.getMgniCtName());
                mgn_mgni.setMgniCtTel(request.getMgniCtTel());
                /* 申請狀態 */
                mgn_mgni.setMgniStatus(request.getMgniStatus());
                mgn_mgni.setMgniUTime(LocalDateTime.now());
                mgnRepository.save(mgn_mgni);
                return "異動成功";
            }
            throw new DataNotFoundException("資料不存在");
        } catch (Exception e) {
            if(e instanceof DataNotFoundException){
                throw e;
            }
            throw new Exception();
        }


    }

    /* delete */
    public String delete(String mgniId) throws Exception {
        MGN_MGNI mgn_mgni = mgnRepository.findByMgniId(mgniId);
        try {
            if (mgn_mgni == null) {
                throw new DataNotFoundException("資料不存在");
            }
            mgnRepository.deleteByMgniId(mgniId);
            return "已刪除";
        } catch (Exception e) {
            if (e instanceof DataNotFoundException) {
                throw e;
            }
            throw new Exception();
        }
    }

}
