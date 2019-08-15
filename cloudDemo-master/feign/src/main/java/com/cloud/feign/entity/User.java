package com.cloud.feign.entity;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/15
 * @Version V1.0
 **/
public class User implements Serializable {
    private String id;
    private String userName;
    private String userPassword;

    public User() {
    }

    public User(String id, String userName, String userPassword) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
