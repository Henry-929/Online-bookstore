package com.book.dao;

import com.book.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);

    public List<Order> queryOders();

    public int changeOrderStatus(String orderId, Integer status);
}
