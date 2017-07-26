package com.pingan.springmvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.pingan.annotation.UserInfo;
import com.pingan.entity.User;
import com.pingan.service.EmpService;
import com.pingan.service.OaService;
import com.pingan.service.StudentService;
import com.pingan.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 
 * @author ZhongLin728
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	   private static final Logger logger = Logger.getLogger(UserController.class);
	   
	    @Autowired
	    private RequestMappingHandlerMapping handlerMapping;
	    
	    /**
	     * JDK自带注入方式。默认按照 name注入!
	     */
		@Resource(name="zhonglin")
	    private StudentService studentService;
		/**
		 * @Autowired  
		 * 按照 Name  注入要加上 @Qualifier    
		 * 默认什么都不加注入 EmpService对象！   
		 * required作用  即时找不到Spring启动也不报错！
		 */
	    @Autowired(required=false)
	    @Qualifier("zhouqiang")
	    private EmpService empService;
	    
	    /**
	     * 注入XML里面配置的Bean 和下面的uservice指向的不是一个 实例！ 由于指定了@Qualifier  所有会面的变量可以随便命名！
	     */
	    @Autowired
	    @Qualifier("SpringUser")
	    private UserService u;
	    
	    /**
	     * 依靠Spring注入，由于存在2个  UserService实例，这里把  userService 当作Name去上下文找bean实例。
	     */
	    @Autowired
	    private UserService userService;
	    
	    
	    @Autowired
	    private User user1;
	    @Autowired
	    private User user2;
	    @Autowired
	    private User user3;
	    
	    @Autowired
	    @Qualifier("oaServiceZhonglin")
	    private OaService oa;
	    
	/**
	 * 
	 * @param model
	 * @param annatationUser
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ApiOperation(value = "根据用户名获取用户对象", httpMethod = "get", response = User.class, notes = "根据用户名获取用户对象")
	public String query(Model model,@UserInfo User annatationUser){
		System.out.println(studentService.getStudent());
		System.out.println(empService.getEmp());
		System.out.println(userService.getUser()+"---"+userService.getUserList());
		System.out.println(u.getUser()+"---"+u.getUserList());
		System.out.println(user1.getName()+user1.getAddress());
		System.out.println(user2.getName()+user2.getAddress());
		System.out.println(user3.getName()+user3.getAddress());
		System.out.println(oa.getOa());
		List<User> list  = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setAddress("湖北"+i);
			user.setName("钟林"+i);
			user.setSex("男");
			user.setBirthday(randomDate("1990-01-05 21:43:21", "2017-07-05 20:10:12"));
			user.setAge(20+i);
			user.setDep("华为DU"+i+"部门");
			list.add(user);
		}
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
		Set<Entry<RequestMappingInfo, HandlerMethod>> entrySet = handlerMethods.entrySet();
		for (Entry<RequestMappingInfo, HandlerMethod> entry : entrySet) {
			logger.info(entry.getKey()+"----"+entry.getValue());
		}
		model.addAttribute("list", list).addAttribute("title", "查询用户信息！").addAttribute("mapping", handlerMethods).addAttribute("annatationUser", annatationUser);
		return "userQuery";
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		System.out.println("2");
		return "add";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(){
		System.out.println("3");
		return "update";
	}
	
	
	
	
	/**
	 * 生成随机时间
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unused")
	private static Date randomDate(String beginDate, String endDate) {
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			Date start = format.parse(beginDate);// 构造开始日期

			Date end = format.parse(endDate);// 构造结束日期

			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。

			if (start.getTime() >= end.getTime()) {

				return null;

			}

			long date = random(start.getTime(), end.getTime());

			return new Date(date);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	private static long random(long begin, long end) {

		long rtn = begin + (long) (Math.random() * (end - begin));

		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值

		if (rtn == begin || rtn == end) {

			return random(begin, end);

		}

		return rtn;

	}


}
