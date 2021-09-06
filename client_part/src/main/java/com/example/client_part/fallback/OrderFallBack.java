package com.example.client_part.fallback;

import com.example.client_part.Interface.OrderInterface;
import com.example.commons.entities.Order;
import org.aspectj.weaver.ast.Or;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderFallBack implements OrderInterface {
    @Override
    public String insertOrder(Map map) {
        return "404";
    }

    @Override
    public String updatePayFlag(int id) {
        return "404";
    }

    @Override
    public String updateSubmitFlag(int id) {
        return "404";
    }

    @Override
    public String deleteOrder(int id) {
        return "404";
    }

    @Override
    public List<Order> getOrderByName(String name) {
        return new LinkedList<>();
    }

    @Override
    public Order getOrderById(int id) {
        return new Order();
    }

    @Override
    public List<Order> getNoPayOrderByName(String name) {
        return new LinkedList<>();
    }
}
