package com.pingan.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义aop切面 通过XML文件指定 
 * @author ZhongLin728
 *
 */
@Component
public class MyInterceptor {
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 前置通知
	 * @param jp
	 */
	public void doBefore(JoinPoint jp) { 
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String formatDate = df.format(new Date());
		  System.out.println(formatDate+"当前访问:"+jp.getTarget().getClass().getName() + "."  + jp.getSignature().getName());
		  Object[] args = jp.getArgs();
		  for (Object object : args) {
			System.out.println("参数:"+object.toString());
		}
		  //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		  Map<String, String[]> parameterMap = request.getParameterMap();
			Iterator<Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<java.lang.String, java.lang.String[]> entry = (Map.Entry<java.lang.String, java.lang.String[]>) iterator
						.next();
				System.out.println("参数"+entry.getKey()+"值"+entry.getValue()[0]);
			}
	 }  
	/**
	 * 后置通知
	 * @param jp
	 */
    public void doAfter(JoinPoint joinPoint) {  
    	
    }  
  /**
   * 环绕通知
   * @param join
   * @return
   * @throws Throwable
   */
    public Object doAround(ProceedingJoinPoint join) throws Throwable {  
        long timeStart = System.currentTimeMillis();  
        Object retVal = join.proceed(); 
        long timeEnd = System.currentTimeMillis();
        System.out.println("方法运行时间" + (timeEnd-timeStart) + " ms");  
        return retVal;  
    }  
  /**
   * 异常通知！
   * @param jp
   * @param ex
   */
    public void doThrowing(JoinPoint jp, Throwable ex) {  
        System.out.println("手动配置运行异常---method " + jp.getTarget().getClass().getName()  
                + "." + jp.getSignature().getName() + " throw exception");  
        System.out.println(ex.getMessage());  
    }  
    
    
}