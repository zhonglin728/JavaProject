package com.cloud.feign.controller;

import com.cloud.feign.rpc.FeiyanApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 获取肺炎API
 */
@RequestMapping(value = "feiyan")
@RestController
public class FeiyanController {


    @Autowired
    private FeiyanApi feiyanApi;

    @GetMapping(value = "news")
    public ResponseEntity<Map> news(){
        Map map = feiyanApi.news();
        ResponseEntity<Map> mapResponseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        return mapResponseEntity;
    }
}
