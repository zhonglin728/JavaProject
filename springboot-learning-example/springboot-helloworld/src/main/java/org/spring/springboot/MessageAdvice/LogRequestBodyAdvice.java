package org.spring.springboot.MessageAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.spring.springboot.annotion.Encryption;
import org.spring.springboot.annotion.EncryptField;
import org.spring.springboot.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName LogRequestBodyAdvice
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/9/12
 * @Version V1.0
 **/
@Slf4j
@RestControllerAdvice(basePackages = "org.spring.springboot.web")
public class LogRequestBodyAdvice implements RequestBodyAdvice {

    @Autowired
    AESUtil aesUtil;

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
        Method method = methodParameter.getMethod();
        //获取自定义注解1  无用代码
        Encryption annotation = method.getAnnotation(Encryption.class);
        //获取自定义注解2 无用代码
        Encryption[] annotationsByType = method.getAnnotationsByType(Encryption.class);


        //获取 参数上面的 注解       二维数组    flatMap 装到List里面
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        List<Annotation> collect = Arrays.stream(parameterAnnotations).flatMap(v -> Arrays.stream(v)).collect(Collectors.toList());
        boolean isRequestBody = collect.stream().anyMatch(v -> v.annotationType().getSimpleName().equals(RequestBody.class.getSimpleName()));
        boolean isEncrypt = collect.stream().anyMatch(v -> v.annotationType().getSimpleName().equals(Encryption.class.getSimpleName()));
        //获取 方法注解！
        if (isEncrypt && isRequestBody){
            try {
                Field[] declaredFields = o.getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    //获取字段注解！
                    if (declaredField.isAnnotationPresent(EncryptField.class)){
                        String fieldName = declaredField.getName();
                        String propertyValue = BeanUtils.getProperty(o, fieldName);
                        String encryptValue = AESUtil.encrypt(propertyValue);
                        BeanUtils.setProperty(o,fieldName,encryptValue);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
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
