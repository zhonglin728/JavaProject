package org.spring.springboot.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: zhonglin
 * @Date: 2019/10/10 19:24
 * @Description:
 */
@Component
@Log
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败" + objectMapper.writeValueAsString(exception.getMessage()));
        //response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        //response.setContentType("application/json;charset=UTF-8");
        //response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
    }
}
