package com.example.order_server.services;

import com.example.commons.entities.Order;
import com.example.order_server.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class OrderSqlServer {
    @Autowired
    OrderDao orderDao;

    /**
     *
     * @param order
     * @return
     */

    public int insertOrder(Order order){
        int i = orderDao.insertOrder(order);
        return i;
    }

    public int updatePayFlag(int id){
        return orderDao.updatePayFlag(id);
    }

    public int updateSubmitFlag(int id){
        return orderDao.updateSubmitFlag(id);
    }

    public int deleteOrder(int id){
        return orderDao.deleteOrder(id);
    }

    public List<Order> getOrderByName(String name){
        return orderDao.getOrderByName(name);
    }

    public Order getOrderById(int id){
        return orderDao.getOrderById(id);
    }

    public List<Order> getNoPayOrderByName(String name){
        return orderDao.getNoPayOrderByName(name);
    }



}
