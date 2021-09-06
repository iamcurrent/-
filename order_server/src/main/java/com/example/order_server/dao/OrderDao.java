package com.example.order_server.dao;

import com.example.commons.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface OrderDao {
    public int insertOrder(Order order);
    public int updatePayFlag(int id);
    public int updateSubmitFlag(int id);
    public int deleteOrder(int id);
    public List<Order> getOrderByName(String name);
    public Order getOrderById(int id);
    public List<Order> getNoPayOrderByName(String name);

}
