package com.pingan.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * Main启动！
 * @author ZhongLin728
 *
 */
@Configuration  
@ComponentScan  
@EnableAutoConfiguration
public class BootMain {
	public static void main(String[] args) {  
        SpringApplication.run(BootMain.class);  
    } 
	
	
	
}
