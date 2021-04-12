package com.book.web;

import com.book.pojo.Cart;
import com.book.pojo.Order;
import com.book.pojo.User;
import com.book.service.OrderService;
import com.book.service.impl.OrderServiceImpl;
import com.book.utils.JdbcUtils;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 发送订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        int statusNum = 0;
        switch (status){
            case "未发货":
                statusNum=0;
                break;
            case "已发货":
                statusNum=1;
                break;
            case "已签收":
                statusNum=2;
                break;
            default:
                statusNum=0;
                break;
        }

        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId,statusNum);

        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 查询所有订单（管理员）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取user id
        User loginUser = (User) req.getSession().getAttribute("user");

        //用户没有登陆先跳转到先登陆，登陆了才能结帐
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = loginUser.getId();
        //调用orderservice生成订单
        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId",orderId);
        //请求转发到订单结算页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }
}
