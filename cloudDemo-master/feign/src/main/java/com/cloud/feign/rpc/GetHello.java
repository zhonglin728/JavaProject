package com.cloud.feign.rpc;

import com.cloud.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 *  构造Fegin区请求 接口！
 */
@FeignClient(value = "ribbon-consumer")
public interface GetHello {


    @RequestMapping(value = "/hello?name=feign",method = RequestMethod.GET)
    public String sayHello();


    @RequestMapping(value = "/findInt",method = RequestMethod.GET)
    public int findInt(@RequestParam(value = "id") int id);

    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public Map saveUser(
            @RequestParam(name = "id",required = false,defaultValue = "0") String id,
            @RequestParam(name = "userName",required = false) String userName,
            @RequestParam(name = "userPassword") String userPassword
    );

    @RequestMapping(value = "/saveUserEntity",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON)
    public Map saveUserEntity(User user);
}
