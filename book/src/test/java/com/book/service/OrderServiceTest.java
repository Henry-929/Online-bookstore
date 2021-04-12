package com.book.service;

import com.book.pojo.Cart;
import com.book.pojo.CartItem;
import com.book.pojo.Order;
import com.book.service.impl.OrderServiceImpl;
import com.book.utils.JdbcUtils;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是："+orderService.createOrder(cart, 1));
    }

    @Test
    public void showAllOrders(){
        OrderService orderService = new OrderServiceImpl();
        for (Order order : orderService.showAllOrders()){
            System.out.println(order);
        }
    }

    @Test
    public void sendOrder(){
        OrderService orderService = new OrderServiceImpl();
        orderService.sendOrder("16181135752121", 0);
        JdbcUtils.commitAndClose();
    }
}