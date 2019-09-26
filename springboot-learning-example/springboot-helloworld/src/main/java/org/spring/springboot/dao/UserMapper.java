package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.spring.springboot.entity.Dict;
import org.spring.springboot.entity.Role;
import org.spring.springboot.entity.Users;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/26 12:39
 * @Description:
 */
//@Mapper
public interface UserMapper {
    List<Users> getUser();
    Dict getDict(Map map);
    List<Role> getRole(String s);
}
