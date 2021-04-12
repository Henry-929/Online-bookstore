package com.book.service;

import com.book.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登陆
     * @param user
     * @return 返回null表示登陆失败
     */
    public User login(User user);

    /**
     * 检查用户名是否可以
     * @param username
     * @return 返回true 表示用户已存在，false表示用户名可用
     */
    public boolean existUsername(String username);
}
