package org.spring.springboot.security;

import org.spring.springboot.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: zhonglin
 * @Date: 2019/10/10 13:54
 * @Description:
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User userDetails = (User) authentication.getPrincipal();
        System.out.println("登录用户：username=" + userDetails.getName() + ", uri=" + request.getContextPath());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
