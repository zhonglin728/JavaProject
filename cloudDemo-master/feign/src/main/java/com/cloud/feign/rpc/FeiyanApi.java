package com.cloud.feign.rpc;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "a",url = "${api.feiyanUrl}")
public interface FeiyanApi {
    @GetMapping(value = "yiqing/news",name = "获取肺炎新闻信息")
    //@Headers({"Content-Type: application/json","Accept: application/json"})
    Map news();
}
