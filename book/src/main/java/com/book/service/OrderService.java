package com.book.service;

import com.book.pojo.Cart;
import com.book.pojo.Order;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

    public List<Order> showAllOrders();

    public void sendOrder(String orderId, Integer status);
}
