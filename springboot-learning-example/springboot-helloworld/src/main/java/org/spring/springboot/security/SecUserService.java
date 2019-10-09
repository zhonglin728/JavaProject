package org.spring.springboot.security;

import com.google.common.collect.Maps;
import org.spring.springboot.entity.Role;
import org.spring.springboot.entity.User;
import org.spring.springboot.entity.Users;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: zhonglin
 * @Date: 2019/10/8 19:02
 * @Description:
 */
//@Service
public class SecUserService<T extends User> implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users user = userService.findByUserName(Maps.newHashMap());
            if (user == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            //用户权限
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (!CollectionUtils.isEmpty(user.getRole())) {
                List<String> roles = user.getRole().stream().map(Role::getRoleKey).collect(Collectors.toList());
                for (String role : roles) {
                    if (!StringUtils.isEmpty(role)) {
                        authorities.add(new SimpleGrantedAuthority(role.trim()));
                    }
                }
            }
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
