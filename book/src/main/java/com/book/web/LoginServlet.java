package com.book.web;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 2、调用service层处理业务
        User loginUser = userService.login(new User(null, username, password, null));
        // 3、根据login()方法返回结果判断登陆是否成功
        if(loginUser == null){
            //把错误信息和回显的表单项信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            //返回null，登陆失败，跳回登陆界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else {
            //登陆成功，跳转到登陆成功界面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
}
