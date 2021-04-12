package com.book.service;

import com.book.pojo.User;
import com.book.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"bbj1689","666666","bbj168@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"admin","admin",null)));
    }

    @Test
    public void existUsername() {
        if (userService.existUsername("bbj1689")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}