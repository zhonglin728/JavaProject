package com.cloud.feign.rpc;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 *
 * @author Hua-cloud
 */
@FeignClient(name = "layui",url = "https://www.layui.com")
public interface LayApi {
    @GetMapping(value = "demo/table/user")
    public Map getLayApi(@RequestParam("page") String page,@RequestParam("limit") String limit);
}
