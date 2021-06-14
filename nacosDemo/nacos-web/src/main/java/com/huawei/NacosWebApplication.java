package com.huawei;

import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
@EnableDiscoveryClient
public class NacosWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosWebApplication.class, args);
    }

}
