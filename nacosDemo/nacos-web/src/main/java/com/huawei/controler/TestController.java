package com.huawei.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhonglin
 * @Title:
 * @Package
 * @Description:
 * @date 2021/6/1621:53
 */
@RestController
public class TestController {

    private RestTemplate restTemplate;

    /*@Autowired
    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://service/getUser/" + str, String.class);
    }
}
