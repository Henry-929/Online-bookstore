package com.book.web;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数username
        String username = req.getParameter("username");
        //调用userservice.existsusername();
        boolean existUsername = userService.existUsername(username);
        //把返回结果封装成map对象
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("existUsername",existUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

        /**
         * 注销
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁session中用户登陆的信息（或销毁seesion）
        req.getSession().invalidate();
        //2、重定向到首页（或登陆页面）
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 处理登陆的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2、调用service层处理业务
        User loginUser = userService.login(new User(null,username,password,null));
        // 3、根据login()方法返回结果判断登陆是否成功
        if(loginUser == null){
            //把错误信息和回显的表单项信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            //返回null，登陆失败，跳回登陆界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else {
            //保存用户登陆后的信息到seesion域中
            req.getSession().setAttribute("user", loginUser);
            //登陆成功，跳转到登陆成功界面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //把所有请求的参数都注入到user对象中
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        // 2、检查验证吗是否正确 先写死，要求验证码为abcde
        if(token != null && token.equalsIgnoreCase(code)){
            //正确，则检查用户名是否可用
            if(userService.existUsername(username)){
                //用户名已存在
                System.out.println("用户名【"+username+"】已存在");

                //把回显信息保存到request域中
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else {
                //用户名可用，调用service保存到数据库
                userService.registUser(new User(null,username,password,email));
                //跳到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        }else {
            //把回显信息保存到request域中
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            //不正确，则跳回注册页面
            System.out.println("验证码【"+code+"】错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}
