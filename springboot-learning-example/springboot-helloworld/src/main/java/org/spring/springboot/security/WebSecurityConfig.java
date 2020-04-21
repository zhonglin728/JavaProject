package org.spring.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @Auther: zhonglin
 * @Date: 2019/10/8 18:58
 * @Description:
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    @Qualifier(value = "secUserService")
    SecUserService secUserService;

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    LoginFailHandler loginFailHandler;

    @Override
    protected void configure(HttpSecurity auth)
            throws Exception {
        //允许基于HttpServletRequest使用限制访问
        auth.authorizeRequests()
                //不需要身份认证
                .antMatchers("/toLogin" , "/**/customer/**").permitAll()
                .antMatchers("/js/**" , "/css/**" , "/images/**" , "/fronts/**" , "/doc/**" , "/toLogin").permitAll()
                .antMatchers("/admin").access("hasAnyRole('ROLE_admin')")
                .antMatchers("/user").access("hasAnyRole('ROLE_chufang','AA','BB')")
                .antMatchers("/home").access("hasRole('ROLE_kanmen')")
                //.antMatchers("/user").hasAnyRole("admin","kanmen","chufang")
                //.antMatchers("/home").hasAnyRole("admin","kanmen","chufang")
                //.hasIpAddress()//读取配置权限配置
                //.antMatchers("/**").access("hasAnyRole('admin')")
                //必须登录才能访问！
                .anyRequest().authenticated()
                //自定义登录界面
                .and().formLogin().loginPage("/toLogin").loginProcessingUrl("/login")
                //默认跳转的 路由！
                .defaultSuccessUrl("/home")
                //.successHandler(loginSuccessHandler)
                //.failureHandler(loginFailHandler)
                .failureUrl("/toLogin?error").permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and().exceptionHandling().accessDeniedPage("/toLogin?deny")
                .and().httpBasic()
                .and().sessionManagement().invalidSessionUrl("/toLogin")
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(secUserService).passwordEncoder(new MyPasswordEncoder());
    }
}
