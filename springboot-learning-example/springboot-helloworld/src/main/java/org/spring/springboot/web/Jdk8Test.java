package org.spring.springboot.web;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spring.springboot.entity.Color;
import org.spring.springboot.entity.Demo;
import org.spring.springboot.entity.Student;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


/**
 * @author Hua-cloud
 */
public class Jdk8Test {
    public static void main(String[] args) throws ParseException, JsonProcessingException {
        // true：默认TypeFilter生效，这种模式会查询出许多不符合你要求的class名
        // false：关闭默认TypeFilter
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
                false);
        // 扫描带有自定义注解的类
        provider.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        // 接口不会被扫描，其子类会被扫描出来
        provider.addIncludeFilter(new AssignableTypeFilter(RestController.class));

        // Spring会将 .换成/  ("."-based package path to a "/"-based)
        // Spring拼接的扫描地址：classpath*:xxx/xxx/xxx/**/*.class
        // Set<BeanDefinition> scanList = provider.findCandidateComponents("com.p7.demo.scanclass");
        Set<BeanDefinition> scanList = provider.findCandidateComponents("org.spring.springboot");

        for (BeanDefinition beanDefinition : scanList) {
            System.out.println(beanDefinition.getBeanClassName());
        }


        List<Student> list =new ArrayList<>();
        list.add(new Student("钟林",10,"24",Color.GREEN));
        list.add(new Student("林俊杰",1,"23",Color.GREEN));
        list.add(new Student("周杰伦",23,"20",Color.GREEN));
        list.add(new Student("蔡依林",56,"45",Color.GREEN));
        list.add(new Student("李现",34,"78",Color.GREEN));
        list.add(new Student("我是中国人",32,"89",Color.GREEN));
        list.add(new Student("陈飞宇",12,"47",Color.GREEN));
        List<Demo> demos = new ArrayList<Demo>();
        //Map原始数据
        System.out.println("原始数据 组装list<demo>******************");
        demos = list.stream().map(student-> new Demo(student.getAge(),student.getSex())).collect(Collectors.toList());
        demos.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //Map 重新包装数据
        System.out.println("MAP多次封装*****************************！");
        list.stream().map(m->{
            Student s = new Student();
            if(m.getAge().equals("18")) {
                s = new Student(m.getAge()+"Map字符串",m.getSex()+100);
            }else {
                s = new Student(m.getAge(),m.getSex());
            }
            return s;
        }).map(m->{
            return new Student(m.getAge()+"再次修改",m.getSex()+1000);
        }).forEach(System.out::println);

        // flatMap  用于将嵌套集合打平
        System.out.println("flatMap****************");
        List<String> collect1 = list.stream().map(Student::getName).collect(Collectors.toList());
        List<String> collect2 = collect1.stream().flatMap(v -> {
            Stream<String> stream = Arrays.stream(v.split(""));
            return stream;
        }).collect(Collectors.toList());


        List<String> collect6 = list.stream().flatMap(v -> Arrays.stream(v.getName().split(""))).collect(Collectors.toList());

        // mapToInt  取出性别以及求和
        int sum1 = list.stream().mapToInt(Student::getSex).sum();

        //filter  只取sex为0
        System.out.println("只取sex为0****************");
        List<Demo> demorm =demos.stream().filter(demo -> demo.getSex() == 0).distinct().collect(Collectors.toList());
        demorm.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });

        //筛选年龄大于12岁
        System.out.println("筛选年龄大于12岁的*************");
        List<Demo> demoFilter =  demos.stream().filter(demo -> Integer.valueOf(demo.getAge()) > 12).collect(Collectors.toList());
        demoFilter.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //sorted 排序1
        System.out.println("年龄排序******************");
        List<Demo> demoSort = demos.stream().sorted((s1, s2) -> s1.getAge().compareTo(s2.getAge())).collect(Collectors.toList());
        demoSort.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //sorted排序2
        List<Student> collect3 = list.stream().sorted((s1, s2) -> s1.getSex() - s2.getSex()).collect(Collectors.toList());

        //sorted排序3
        List<Student> sortedCivilStudents = list.stream()
                .filter(student -> "土木工程".equals(student.getSex())).sorted((s1, s2) -> s1.getSex() - s2.getSex())
                //.limit(2)
                .collect(Collectors.toList());

        //倒序  reversed
        System.out.println("年龄倒序****************");
        ArrayList<Demo> demoArray = (ArrayList<Demo>) demos;
        Comparator<Demo> comparator = (h1, h2) -> h1.getAge().compareTo(h2.getAge());
        demos.sort(comparator.reversed());
        demos.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //多条件正序  thenComparing
        System.out.println("多条件排序正序****************");
        demoArray.sort(Comparator.comparing(Demo::getSex).thenComparing(Demo::getAge));
        demoArray.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //多条件倒序 reversed       thenComparing
        System.out.println("多条件排序倒序 sex  倒序****************");
        demoArray.sort(Comparator.comparing(Demo::getSex).reversed().thenComparing(Demo::getAge));
        demoArray.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });

        //groupingBy 按照年龄分组
        System.out.println("根据age分组结果为Map****************");
        Map<String, List<Demo>> map = demos.stream().collect(Collectors.groupingBy(Demo::getAge));

        //2.分组，并统计其中一个属性值得sum或者avg:id总和
        Map<String,Integer> result3=list.stream().collect(
                Collectors.groupingBy(Student::getAge,Collectors.summingInt(Student::getSex))
        );


        System.out.println("Optional  orElse  的使用*******************");
        //Optional isPresent  不建议使用
        Optional<List<Student>> list1 = Optional.ofNullable(list);
        if (list1.isPresent()){
            List<Student> students = list1.get();
        }
        //Optional  List集合使用！
        List<Student> students = Optional.ofNullable(list).orElse(list);
        //Optiona 字符串使用*************************
        Student student = new Student("周杰伦",30,"23",Color.GREEN);
        String s1 = Optional.ofNullable(student).map(v -> v.getName()).orElse("..");
        //Optional  对象使用！
        Student student3 = Optional.ofNullable(student).orElse(new Student("刘德华",57,"2",Color.YELLO));
        //Optional  get  使用！
        Student student4 = Optional.ofNullable(student).get();
        //序列化JSON
        String s = new ObjectMapper().writeValueAsString(student3);

        //Collectors.toCollection(ArrayList::new)
        System.out.println("stream转换成toArray,ArrayList集合数据*******************");
        String[] strArray  = Stream.of("hello","world","tom").toArray(String[]::new);
        List<String> strings = Arrays.asList(strArray);
        List<String> list2 = Stream.of("hello","world","tom").collect(toCollection(ArrayList::new));

        //Stream.of 合并 list
        System.out.println("stream of合并集合数据*******************");
        List<?> collect4 = Stream.of(
                list, demos
        ).flatMap(e->{
            return e.stream();
        }).collect(Collectors.toList());

        //Stream.concat 合并 list
        System.out.println("stream.concat合并集合数据*******************");
        List<Object> collect5 = Stream.concat(list.stream(), demos.stream()).collect(Collectors.toList());

        // anyMatch 任意一个人匹配就返回  true
        boolean allMatch = list.stream().anyMatch((v)->v.getAge().equals("45"));
        System.out.println(allMatch);

        //对象类型 findFirst 的使用*******************
        System.out.println("findFirst 的使用*******************");
        Optional<Student> findFirst = list.stream().filter(m->m.getAge().equals("12")).findFirst();
        Student student1 = findFirst.orElse(new Student());
        findFirst.ifPresent(System.out::println);
        if(findFirst.isPresent()) {
            Student student2 = findFirst.get();
            System.out.println(student2);
        }

        // reduce 求聚合函数求性别的max和sum值    ->  【 (s->s.getSex()) 可以简写为  (Student::getSex)】
        int max = list.parallelStream().mapToInt(Student::getSex).reduce(0,Integer::max);
        System.out.println(max);
        int sum = list.parallelStream().map(v->v.getSex()).reduce(0,Integer::sum);
        System.out.println(sum);

        //按照字段去除重复 数据
        ArrayList<Student> collect = list.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getAge))), ArrayList::new)
        );

        //JDK8  时间API
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = now.format(dateTimeFormatter);


        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(date);
        Date parse = simpleDateFormat.parse(format1);


        // 枚举的使用！
        System.out.println( Color.getName2(2));
        System.out.println(Color.getIndex2("绿色"));



        //灵活传递表达式
        filterPre1(list,(str)->str.equals("周杰伦"));
        //匹配 Name为周杰伦的数据
        filterPre2(list,(str)->str.getName().equals("周杰伦"));
        //匹配年龄大于44的数据
        filterPre2(list,(str)->Integer.valueOf(str.getAge())>44);
        // 输出所有数据！
        filterPre2(list,(str)->true);


    }

    /**
     *Predicate用法1
     * @param list
     * @param condition
     */
    public static void filterPre1(List<Student> list, Predicate condition) {
        for(Student v: list){
            System.out.println(v);
            //test  匹配表达式！
            if (condition.test(v.getName())){
                break;
            }
        }
    }

    /**
     *Predicate用法2
     * @param list
     * @param condition 传递灵活参数！
     */
    public static void filterPre2(List<Student> list, Predicate<Student> condition) {
        List<Student> collect = list.stream().filter(condition).collect(Collectors.toList());
        System.out.println(collect.size());
    }

}
