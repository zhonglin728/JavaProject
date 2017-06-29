package com.pingan.util;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Java 扫描包工具
 * @author Administrator
 *
 */
@Component
public class ScanPackage implements InitializingBean{
	
	public static <T> void main(String[] args) throws ClassNotFoundException  {
		
	}
	
	
	@SuppressWarnings("unused")
	@Override
	/**
	 * 实例化bean的时候 扫描包service 并实例化撞到Map里面 
	 */
	public void afterPropertiesSet() throws Exception {
		Map<String, Object> map = SpringScanPackage();
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			System.out.println(entry);
		}
	
	}
	
	
	/**
	 * 利用 spring  PathMatchingResourcePatternResolver扫描包 class  并实例化service注解的类
	 * 2017-6-29 21:44:06
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> SpringScanPackage(){
		try {
			Map<String, Object> mapService = new HashMap<>();
			PathMatchingResourcePatternResolver pa = new PathMatchingResourcePatternResolver();
			Resource[] resources = pa.getResources("classpath*:com/pingan/*/*.class");
			for (Resource resource : resources) {
				String classPackageAsResourcePath = resource.getDescription();
				String pack = (("com"+classPackageAsResourcePath.split("\\\\com")[1]).replace("\\", ".")).split(".class")[0];
				//System.out.println(pack);
				Class<?> forName = Class.forName(pack);
				if(forName.isAnnotationPresent(Service.class)){
					String className = forName.getSimpleName();
					className = className.substring(0,1).toLowerCase()+className.substring(1, className.length());
					mapService.put(className,  forName.newInstance());
				}
			}
			return mapService;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/** 
     * 扫描包下的所有文件 的注解
     * 
     *  com.pingan.pilot.pact
     *  
     * @param Package 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 调用   new HttpClientUtil().scanPackage("com.pingan");
     */  
    public  void scanPackage(String Package) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
         URL url = this.getClass().getClassLoader().getResource("" + Package.replaceAll("\\.", "/"));// 将所有的.转义获取对应的路径  
        String pathFile = url.getFile();  
        File file = new File(pathFile);  
        String fileList[] = file.list();  
        for (String path : fileList) {  
            File eachFile = new File(pathFile+ "/" +path);  
            if (eachFile.isDirectory()) {  
                scanPackage(Package +"/" +eachFile.getName());  
            } else {  
                //packageNames.add(Package + "." + eachFile.getName());  
            	String name = (Package + "." + eachFile.getName()).replaceAll("/", ".");
            	if(name.indexOf("$") == -1 && name.indexOf(".class")>-1){
                	Class<?> forName = Class.forName(name.replaceAll(".class", ""));
                	Method[] declaredMethods = forName.getDeclaredMethods();
                	for (Method method : declaredMethods) {
						Annotation[] annotations = method.getAnnotations();
						for (Annotation annotation : annotations) {
							System.out.println(method.getName()+"--"+annotation);
						}
					}
            	}
            }  
        }  
    }
    
    
	/**
	 *  扫描包下面的class 反射方法！
	 * @param path
	 * @throws ClassNotFoundException
	 * 调用    findFile("com/pingan");
	 * 自己写 有bug
	 */
	public static  void findFile(String path) throws ClassNotFoundException{
		URL url = Thread.currentThread().getContextClassLoader().getResource(path);
		File file = new File(url.getFile());
		if(file.isDirectory()){
			String[] list = file.list();
			for (String string : list) {
				findFile(path+"/"+string);
			}
		}
		else{
			if(file.getName().indexOf("$")==-1 && file.getName().endsWith(".class")){
				Class<?> forName = Class.forName(path.replaceAll("/", ".").replaceAll(".class", ""));
				Method[] methods = forName.getDeclaredMethods();
				for (Method method : methods) {
					Annotation[] annotations = method.getAnnotations();
					for (Annotation annotation : annotations) {
						System.out.println(method.getName()+"---"+annotation);
					}
				}
			}
			
		}
	}


	

}
