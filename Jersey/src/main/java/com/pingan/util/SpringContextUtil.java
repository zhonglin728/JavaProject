package com.pingan.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 获取Spring的Bean
 * @author ZhongLin728
 *
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
	// Spring应用上下文环境  
    private static ApplicationContext applicationContext;  
    /** 
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境 
     *  
     * @param applicationContext 
     */  
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {  
    	System.err.println("-----setApplicationContext");
        SpringContextUtil.applicationContext = applicationContext;  
    }  
    /** 
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext() {  
    	System.err.println("-----getApplicationContext");
        return applicationContext;  
    }  
    /** 
     * 获取对象 
     *  
     * @param name 
     * @return Object
     * @throws BeansException 
     */  
    public static Object getBean(String name) throws BeansException {  
    	//System.err.println("-----getBean");
        return applicationContext.getBean(name);  
    }  
    /**
     * 自定义加载的方法！
     */
    public  void getApplication(){
    	System.err.println("-----大家好我是Spring初始化的时候加载的Method");
    }
}
