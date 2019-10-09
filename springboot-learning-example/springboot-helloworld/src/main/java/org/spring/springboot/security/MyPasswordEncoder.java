package org.spring.springboot.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Auther: zhonglin
 * @Date: 2019/10/8 19:19
 * @Description:
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
