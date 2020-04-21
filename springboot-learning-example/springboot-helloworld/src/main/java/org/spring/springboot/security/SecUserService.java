package org.spring.springboot.security;

import com.google.common.collect.Lists;
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
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Auther: zhonglin
 * @Date: 2019/10/8 19:02
 * @Description:
 */
@Service
public class SecUserService<T extends User> implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users user = userService.findByUserName(Maps.newHashMap());
            Assert.notNull(user, "用户名不存在");
            //用户权限
            List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(user.getRole())) {
                Set<String> roles = user.getRole().stream().map(v -> "ROLE_" + v.getRoleKey().trim()).collect(Collectors.toSet());
                roles.forEach(v -> {
                    if (!StringUtils.isEmpty(v)) {
                        authorities.add(new SimpleGrantedAuthority(v));
                    }
                });
            }
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
