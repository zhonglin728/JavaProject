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
 * name：指定FeignClient的名称，如果项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
 * url: url一般用于调试，可以手动指定@FeignClient调用的地址
 * decode404:当发生http 404错误时，如果该字段位true，会调用decoder进行解码，否则抛出FeignException
 * configuration: Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract
 * fallback: 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口
 * fallbackFactory: 工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码
 * path: 定义当前FeignClient的统一前缀
 * 构造Fegin区请求 接口！
 */
@FeignClient(name = "ribbon-consumer" , qualifier = "getHello")
public interface GetHello {


    @RequestMapping(value = "/hello?name=feign" , method = RequestMethod.GET)
    public String sayHello();


    @RequestMapping(value = "/findInt" , method = RequestMethod.GET)
    public int findInt(@RequestParam(value = "id") int id);

    @RequestMapping(value = "/saveUser" , method = RequestMethod.POST)
    public Map saveUser(
            @RequestParam(name = "id" , required = false, defaultValue = "0") String id,
            @RequestParam(name = "userName" , required = false) String userName,
            @RequestParam(name = "userPassword") String userPassword
    );

    @RequestMapping(value = "/saveUserEntity" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    public Map saveUserEntity(User user);
}
