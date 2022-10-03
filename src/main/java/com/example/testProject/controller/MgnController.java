package com.example.testProject.controller;

import com.example.testProject.controller.dto.request.CreateMgniRequest;
import com.example.testProject.controller.dto.request.UpdateMgnRequest;
import com.example.testProject.controller.dto.response.StatusResponse;
import com.example.testProject.exception.DataNotFoundException;
import com.example.testProject.exception.ParamInvalidException;
import com.example.testProject.model.MgnRepository;
import com.example.testProject.model.entriy.MGN_MGNI;
import com.example.testProject.service.MgnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/mgn",produces = {"application/xml","application/json"})
@Validated
public class MgnController {

    @Autowired
    MgnService mgnService;
    private StatusResponse statusResponse;

    @Autowired
    MgnRepository mgnRepository;
    /* Try Specification */

    @PostMapping("/get")
    public List<MGN_MGNI> get(@RequestBody StatusResponse response){
        // lambda可簡化
        Specification<MGN_MGNI> specification = new Specification<MGN_MGNI>() {
            @Override
            public Predicate toPredicate(Root<MGN_MGNI> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (response.getMgniId() != null){
                    predicates.add(criteriaBuilder.equal(root.get("mgniId"), response.getMgniId()));
                }

                // 模糊搜尋 name
                if(!"".equals(response.getMgniCtName())){
                    predicates.add(criteriaBuilder.like(root.get("mgniCtName"), "%" + response.getMgniCtName() + "%"));

                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };

        //倒序
        Sort sort = Sort.by(Sort.Direction.DESC, "mgniTime");
        //查詢頁數, 查詢每頁筆數, 排序
        PageRequest page = PageRequest.of(0, 3, sort);
        Page<MGN_MGNI> mgn_mgnis = mgnRepository.findAll(specification, page);

        return mgn_mgnis.getContent();
    }



//    @GetMapping("/{mgniId}")
    @GetMapping(value = "/{mgniId}")
    public StatusResponse getAllIds(@PathVariable String mgniId) throws Exception{
        return mgnService.getAllId(mgniId);
    }


    @PostMapping
    public CreateMgniRequest createMgni(@RequestBody @Valid CreateMgniRequest request) throws Exception{
        try {
            return mgnService.createMGNI(request);
        }catch (Exception e){
            if(e instanceof MethodArgumentNotValidException){
                throw e;
            }
            if(e instanceof DataNotFoundException){
        throw e;
    }
            if(e instanceof ParamInvalidException){
        throw e;
    }
            throw e;
}
    }

    @PutMapping("/{mgniId}")
    public String updateMgni(@PathVariable String mgniId, @RequestBody UpdateMgnRequest request)throws Exception{
        try {
            return mgnService.update(mgniId, request);
        }catch (Exception e){
            throw new Exception();
        }
    }

    @DeleteMapping("/{mgniId}")
    public String deleteMgni(@PathVariable String mgniId) throws Exception{
        return mgnService.delete(mgniId);
    }
}
