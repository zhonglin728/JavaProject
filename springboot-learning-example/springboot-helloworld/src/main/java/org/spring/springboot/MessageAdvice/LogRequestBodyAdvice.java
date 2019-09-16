package org.spring.springboot.MessageAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @ClassName LogRequestBodyAdvice
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/9/12
 * @Version V1.0
 **/
@Slf4j
public class LogRequestBodyAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method=methodParameter.getMethod();
        ObjectMapper objectMapper = new ObjectMapper();
        String s= "";
        try {
             s = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("{}.{}:{}",method.getDeclaringClass().getSimpleName(),method.getName(),s);
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method=methodParameter.getMethod();
        log.info("{}.{}",method.getDeclaringClass().getSimpleName(),method.getName());
        return o;
    }


}
