package org.spring.springboot.web;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.spring.springboot.exception.OrderPeriodException;
import org.spring.springboot.response.ResponseResult;
import org.spring.springboot.service.StudentService;
import org.spring.springboot.service.EurekaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
            throw  new OrderPeriodException("","自定义错误消息！");
        }
        return "Hello,World!";

    }

    /**
     * 测试 200 状态码
     * @return
     */
    @RequestMapping("getOk")
    public ResponseEntity<ResponseResult> getXx(){
        Map client = eurekaConfig.getServiceUrl();
        String s = Optional.ofNullable(client.get("defaultZone")).map(Object::toString).orElse("default");
        List<String> strings = Splitter.onPattern("://").omitEmptyStrings().trimResults().splitToList(s);
        ImmutableMap<Object, Object> map = ImmutableMap.builder().put(strings.get(0), strings.get(1)).build();
        ResponseResult res = new ResponseResult<>(map);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    /**
     * 测试 401状态码
     * @return
     */
    @RequestMapping("getUnauth")
    public ResponseEntity<ResponseResult> getUnauth(){
        Map<String, List> data = ImmutableMap.of("list",ImmutableList.of("张飞", "关于", "刘备") );
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("hh","zhonglinxxoo");
        httpHeaders.add("jj","asasaasaa");

        ResponseResult result = new ResponseResult("200", "请求成功！", data);
        ResponseEntity<ResponseResult> resultEntity = new ResponseEntity<>(result,httpHeaders, HttpStatus.UNAUTHORIZED);

        return resultEntity;
    }

}
