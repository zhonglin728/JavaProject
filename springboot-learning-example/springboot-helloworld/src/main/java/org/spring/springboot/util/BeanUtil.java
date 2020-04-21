package org.spring.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spring.springboot.entity.User;
import org.spring.springboot.enums.ColorEnum;
import org.spring.springboot.entity.Demo;
import org.spring.springboot.entity.Student;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName BeanUtil
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/13
 * @Version V1.0
 **/
public class BeanUtil {

    private static List<Student> list1 = new ArrayList<Student>();

    static {
        list1.add(new Student("e" , 10, "24" , ColorEnum.GREEN));
        list1.add(new Student("b" , 1, "23" , ColorEnum.GREEN));
        list1.add(new Student("d" , 23, "20" , ColorEnum.GREEN));
        list1.add(new Student("c" , 56, "45" , ColorEnum.GREEN));
        list1.add(new Student("f" , 34, "78" , ColorEnum.GREEN));
        list1.add(new Student("g" , 32, "89" , ColorEnum.GREEN));
        list1.add(new Student("a" , 12, "47" , ColorEnum.GREEN));
    }

    public static void main(String[] args) throws JsonProcessingException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Student student = new Student("e" , 10, "24" , ColorEnum.GREEN);
        Demo demo = new Demo();
        //单个 复制！
        BeanUtils.copyProperties(student, demo);
        System.out.println(new ObjectMapper().writeValueAsString(demo));


        //List  集合转换！
        List<Demo> collect = list1.stream().map(v -> {
            Demo demo1 = new Demo();
            BeanUtils.copyProperties(v, demo1);
            return demo1;
        }).collect(Collectors.toList());
        System.out.println(new ObjectMapper().writeValueAsString(collect));


        //调用方法转换List
        List<Demo> demos = copyList(list1, Demo.class);
        //调用方法转换Bean
        Demo demo1 = copyBean(student, Demo.class);


        User user = User.builder().id(1).name("钟林").age(24).address("湖北十堰").build();
        Student student1 = new Student();
        //apache 复制对象功能比Spring强大  遇到不同类型的字段也可以转换！
        //转换后对象/源对象
        org.apache.commons.beanutils.BeanUtils.copyProperties(student1, user);

        Student student2 = new Student();
        //Spring 复制对象！
        //源对象/转换后对象
        BeanUtils.copyProperties(user, student2);


    }

    /**
     * copy List 集合VO到DTO转换！
     *
     * @param sourceList  源数据
     * @param targetClass 转换后数据
     * @param <E>
     * @param <C>
     * @return
     */
    public static <E, C> List<C> copyList(List<E> sourceList, Class<C> targetClass) {
        List<C> collect = sourceList.stream().map(v -> {
            C o = null;
            try {
                o = targetClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(v, o);
            return o;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * copy  bean对象  使用对象之间的转换！
     *
     * @param t      源数据
     * @param rClass 转换后的数据！
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R copyBean(T t, Class<R> rClass) {
        R r = null;
        try {
            r = rClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(t, r);
        return r;
    }


}
