package com.book.dao;

import com.book.dao.impl.OrderDaoImpl;
import com.book.pojo.Order;
import com.book.utils.JdbcUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest {

    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0,1));
    }

    @Test
    public void queryOders(){
        for (Order order : orderDao.queryOders()){
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus(){
        int i = orderDao.changeOrderStatus("16181135752121", 1);
        System.out.println(i);
        JdbcUtils.commitAndClose();
    }
}