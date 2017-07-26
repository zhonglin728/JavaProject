package com.pingan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.pingan.entity.User;
/**
 * Spring 定时任务
 * @author ZhongLin728
 *
 */
@Component
public class SpringTimerTest {
	private int flag = 0;
	private Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private User user1;
	 /**
	  * 启动时执行一次，之后每隔3秒执行一次
	  */
	 
	 //@Scheduled(fixedRate = 1000 * 3)
	 public String print() {
		 User user =  (User) ContextLoader.getCurrentWebApplicationContext().getBean("user1");
		 User users = (User) SpringContextUtil.getBean("user2");
		 System.out.println(user.toString()+users.toString());
		 flag++;
		 log.info(user.getName() + flag + users.getAddress());
		 log.info("timer running..."+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		 return users.getName() + flag + users.getAddress() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	 }
}
