package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Cart;
import com.book.pojo.CartItem;
import com.book.service.BookService;
import com.book.service.impl.BookeServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CarServlet extends BaseServlet{

    private BookService bookService = new BookeServiceImpl();

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数 商品编号，商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null){
            //修改商品数量
            cart.updateCount(id, count);
            //重定向回原来商品所在的地址 referer是浏览器请求头中的地址
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null){
            //清空购物车
            cart.clear();
            //重定向回原来商品所在的地址 referer是浏览器请求头中的地址
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null){
            //删除了购物车商品项
            cart.deleteItem(id);
            //重定向回原来商品所在的地址 referer是浏览器请求头中的地址
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

        /**
         * 加入购物车
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookservice.querybookbyid（id）的到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转化为cartitem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart == null){
            //创建购物车
            cart = new Cart();
            //把购物车放入session中
            req.getSession().setAttribute("cart", cart);
        }

        //调用car.additem(caritem) 添加商品项
        cart.addItem(cartItem);

        //获取最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        //重定向回原来商品所在的地址 referer是浏览器请求头中的地址
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookservice.querybookbyid（id）的到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转化为cartitem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart == null){
            //创建购物车
            cart = new Cart();
            //把购物车放入session中
            req.getSession().setAttribute("cart", cart);
        }

        //调用car.additem(caritem) 添加商品项
        cart.addItem(cartItem);

        //获取最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        //返回购物车总的商品数量，和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJsonString);
    }
}
