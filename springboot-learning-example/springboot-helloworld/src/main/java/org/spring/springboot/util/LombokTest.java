package org.spring.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.val;
import org.spring.springboot.entity.Student;
import org.spring.springboot.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName LombokTest
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/12
 * @Version V1.0
 **/
@Log
public class LombokTest {

    public static void main(String arg []) throws JsonProcessingException {
        String [] arr = new String []{"a","b"};

        val user = User.builder().id(100).age(23).address("湖北襄阳！").build();
        System.out.println(findUser(user));
        //System.out.println(findUser(null));
        System.out.println(findArr(arr));
        log.info(new ObjectMapper().writeValueAsString(user));


    }


    /**
     *
     * @param user
     * @return
     */
    public static String findUser(@NonNull User user){
        return Optional.ofNullable(user).map(User::getName).orElse("****null****");
    }

    /**
     *
     * @param s
     * @return
     */
    public static List<String> findArr(@org.springframework.lang.NonNull String ...s){
       val collect = Arrays.stream(s).map(String::toUpperCase).collect(Collectors.toList());
        return collect;
    }



}
