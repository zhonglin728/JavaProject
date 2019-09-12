package org.spring.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.spring.springboot.entity.Student;
import org.spring.springboot.entity.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonTest
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/16
 * @Version V1.0
 **/
public class JsonTest {


    private static List  list = new ArrayList();
    static {
        list.add(User.builder().id(1).name("钟林").age(24).address("湖北十堰").build());
        list.add(User.builder().id(2).name("李现").age(25).address("湖北荆州").build());
    }

    public static void main(String [] a) throws IOException, InvocationTargetException, IllegalAccessException {
        User user = User.builder().id(1).name("钟林").age(24).address("湖北十堰").build();
        ObjectMapper objectMapper = new ObjectMapper();


        //bean转Map
        Map map = objectMapper.convertValue(user, Map.class);

        //Map转Bean
        User user1 = objectMapper.convertValue(map, User.class);

        //bean转String
        String s = objectMapper.writeValueAsString(user1);

        //String转Bean
        User user2 = objectMapper.readValue(s, User.class);

        //List转字符串
        String s1 = objectMapper.writeValueAsString(list);

        //字符串转List
        List<User> ll = objectMapper.readValue(s1, new TypeReference<List<User>>() {
        });


    }
}
