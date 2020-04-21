package com.pingan.cxf;

import java.util.List;

import javax.jws.WebService;

import com.pingan.entity.User;

@WebService
public interface IHelloWorldService {

    List<User> query(String name);

    void add(User user);

    void delete(String id);

    Integer update(User user);


}
