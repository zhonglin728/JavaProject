package org.spring.springboot.web;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.spring.springboot.exception.OrderPeriodException;
import org.spring.springboot.interfunc.EmployeeInter;
import org.spring.springboot.service.StudentService;
import org.spring.springboot.util.EurekaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * Spring Boot HelloWorld 案例
 *
 *
 * @author bysocket
 * @date 16/4/26
 */
@RequestMapping(value = "test")
@RestController
public class HelloWorldController {
    @Autowired
    StudentService studentService;
    @Autowired
    EurekaConfig eurekaConfig;

    @RequestMapping("err")
    public String sayHello() {
        try {
            System.out.println(1/0);
        }catch (Exception e){
            throw  new OrderPeriodException("500","自定义错误消息！");
        }
        return "Hello,World!";

    }

    /**
     * 测试 200 状态码
     * @return
     */
    @RequestMapping("getOk")
    public ResponseEntity<Object> getXx(){
        Map client = eurekaConfig.getClient();
        return new ResponseEntity<Object>(Optional.ofNullable(client.get("serviceUrl")).map(Object::toString).orElse("default"),HttpStatus.OK);
    }


    /**
     * 测试 401状态码
     * @return
     */
    @RequestMapping("getUnauth")
    public ResponseEntity<Object> getUnauth(){
        Map<String, String> data = ImmutableMap.of("code", "测试401");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("hh","zhonglinxxoo");
        httpHeaders.add("jj","asasaasaa");
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>(data,httpHeaders, HttpStatus.UNAUTHORIZED);
        return objectResponseEntity;
    }

}
