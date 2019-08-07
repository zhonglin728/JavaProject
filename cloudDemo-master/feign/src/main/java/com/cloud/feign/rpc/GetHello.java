package com.cloud.feign.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ribbon-consumer")
public interface GetHello {


    @RequestMapping(value = "/hello?name=feign",method = RequestMethod.GET)
    public String sayHello();


    @RequestMapping(value = "/findInt",method = RequestMethod.GET)
    public int findInt(@RequestParam(value = "id") int id);
}
