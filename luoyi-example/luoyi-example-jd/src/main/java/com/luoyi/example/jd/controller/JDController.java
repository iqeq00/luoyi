package com.luoyi.example.jd.controller;

//import com.luoyi.example.jd.cpt.vop.JDServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class JDController {

//    @Autowired
//    private JDServiceApi jdServiceApi;


    @GetMapping("/logistics")
    public String logistics(String jdOrderId) {

//        jdServiceApi.getLogistics(jdOrderId);
        return "success";
    }

}