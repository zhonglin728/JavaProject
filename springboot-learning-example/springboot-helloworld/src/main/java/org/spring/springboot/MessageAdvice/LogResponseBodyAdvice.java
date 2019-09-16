package org.spring.springboot.MessageAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @ClassName LogResponseBodyAdvice
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/9/12
 * @Version V1.0
 **/
@Slf4j
@RestControllerAdvice(basePackages = "org.spring.springboot.web")
public class LogResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Method method=methodParameter.getMethod();
        String url=serverHttpRequest.getURI().toASCIIString();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = "";
        try {
           s = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {

        }
        log.info("{}.{},url={},result={}",method.getDeclaringClass().getSimpleName(),method.getName(),url,s);
        return o;

    }
}
