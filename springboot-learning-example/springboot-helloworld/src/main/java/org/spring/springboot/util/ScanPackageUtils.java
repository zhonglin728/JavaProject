package org.spring.springboot.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @ClassName ScanPackageUtils
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/14
 * @Version V1.0
 **/
public class ScanPackageUtils {
    public static void main(String [] args ){
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


    }
}
