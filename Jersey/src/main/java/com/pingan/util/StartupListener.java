package com.pingan.util;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class StartupListener implements ApplicationListener{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("加载配置文件！");
//		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
//		Properties prop = new Properties();
//		prop.load(in);
		
	}
	

}
