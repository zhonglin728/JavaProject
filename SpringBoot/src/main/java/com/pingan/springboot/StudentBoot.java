package com.pingan.springboot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author ZhongLin728
 *
 */
@RestController
public class StudentBoot {
	@RequestMapping(value="/student")
	public List<?> view(){
		List<Map> list = new ArrayList<Map>();
		for(int i=0;i<100;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "钟林"+i);
			map.put("age", i);
			list.add(map);
		}
		return list;
	}

}
