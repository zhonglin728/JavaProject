package org.spring.springboot.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: zhonglin
 * @Date: 2019/10/8 19:19
 * @Description:
 */
public class MD5Util {
    public static final int time = 5;

    public static final String SALT = "springsecurity";

    /**
     * 密码加密方法
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }
        try {
            for (int i = 0; i < time; i++) {
                byte[] bytes = digest.digest((password + SALT).getBytes("UTF-8"));
                password = String.format("%032x", new BigInteger(1, bytes));
            }
            return password;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encode("123456"));
    }


}
