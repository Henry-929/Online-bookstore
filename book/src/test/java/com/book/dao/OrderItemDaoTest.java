package com.book.dao;

import com.book.dao.impl.OrderItemDaoImpl;
import com.book.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOderItem(new OrderItem(null,"javascript从入门到精通3",2,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOderItem(new OrderItem(null,"java",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));

    }
}