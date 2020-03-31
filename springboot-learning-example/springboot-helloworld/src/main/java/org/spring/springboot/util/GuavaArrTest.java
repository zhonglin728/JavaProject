package org.spring.springboot.util;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.spring.springboot.enums.ColorEnum;
import org.spring.springboot.entity.Student;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

/**
 * @ClassName GuavaTest
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/13
 * @Version V1.0
 **/
public class GuavaArrTest {

    private  static   List<Student> list1 = new ArrayList<Student>();
    private  static   List<Student> list2 = new ArrayList<Student>();

    static {
        list1.add(new Student("e",10,"24", ColorEnum.GREEN));
        list1.add(new Student("b",1,"23", ColorEnum.GREEN));
        list1.add(new Student("d",23,"20", ColorEnum.GREEN));
        list1.add(new Student("c",56,"45", ColorEnum.GREEN));
        list1.add(new Student("f",34,"78", ColorEnum.GREEN));
        list1.add(new Student("g",32,"89", ColorEnum.GREEN));
        list1.add(new Student("a",12,"47", ColorEnum.GREEN));

        list2.add(new Student("周杰伦",3,"25", ColorEnum.YELLO));
        list2.add(new Student("罗志祥",7,"13", ColorEnum.RED));

    }




    public static void main(String arg []) {
        List<Student> e = list1.stream().filter(v ->v.getName().equals("e")).collect(Collectors.toList());
        //String s = Optional.ofNullable(" ssss  ").orElseThrow();
        System.out.println(Optional.ofNullable(" ssss  "));
        System.out.println(CollectionUtils.isEmpty(new ArrayList<>()));
        System.out.println(CollectionUtils.isEmpty(new HashMap<>()));
        Map map = new HashMap();
        map.put("a",1);
        System.out.println(CollectionUtils.isEmpty(map));
        System.out.println(Collections.emptyList());

        //排序
        List<Student> collect2 = list1.stream().sorted((v1, v2) -> v1.getSex() - v2.getSex()).collect(Collectors.toList());
        List<Student> collect3 = list1.stream().sorted((v1, v2) -> v1.getAge().compareTo(v2.getAge())).collect(Collectors.toList());
        List<Student> collect4 = list1.stream().sorted((v1, v2) -> v2.getName().compareTo(v1.getName())).collect(Collectors.toList());

        //Set<Student> collect = of.stream().flatMap(v-> v.stream()).collect(Collectors.toSet());
        ImmutableList<Object> build = ImmutableList.builder().add(list1).add(list2).build();




        //---------------------------------------------------------------------------------------------------
        //  Joiner.on用法  list转string
        List<String> collect = list1.stream().map(v -> v.getName()).collect(Collectors.toList());
        String join = Joiner.on("&").skipNulls().join(collect);
        System.out.println(join);

        ImmutableList<String> of2 = ImmutableList.of("a", "b");
        // 将List集合转成String，  JDK 原生写法
        String join1 = String.join(",", of2);
        // 将List集合转成String，  JDK8 Collectors.joining写法
        String collect5 = of2.stream().collect(Collectors.joining("."));

        //字符串转List
        String[] split = join.split("&");
        List<String> strings = Arrays.asList(split);
        List<String> collect1 = Arrays.stream(split).collect(Collectors.toList());

        //StringJoiner用法 jdk 底层
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add("cx").add("er").add("fg");

        //ImmutableMap使用  可以用来转枚举
        ImmutableMap<String, String> of1 = ImmutableMap.of("0", "儿童", "1", "青年");
        String s = of1.get("1");
        of1.forEach((k,v)->{
            //System.out.println(v);
        });
        ImmutableMap<Object, Object> build3 = ImmutableMap.builder().put("a", 1).put("b", 2).build();

        // ImmutableTableList用法
        List<List<Student>> of = ImmutableList.of(list1, list2);
        List<String> list3 = Stream.of("hello","world","tom").collect(toCollection(ArrayList::new));
        List<String> list4 = Stream.of("hello","world","tom").collect(Collectors.toList());


        List<Object> build1 = ImmutableList.builder().add(list1).add(list2).build();
        Map<Object, Object> build2 = ImmutableMap.builder().put("z", "a").put("d", "b").build();


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(date);

        String xx = "钟林/00934";
        String[] split1 = StringUtils.split(xx, "/");
        System.out.println(split);











    }



}
