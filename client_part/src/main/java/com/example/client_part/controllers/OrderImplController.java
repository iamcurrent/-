package com.example.client_part.controllers;

import com.example.client_part.Interface.OrderInterface;
import com.example.commons.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SuppressWarnings("all")
public class OrderImplController {
    @Autowired
    OrderInterface orderInterface;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/submitOrder")
    @ResponseBody
    public String submitOrder(@RequestBody Map map){
        System.out.println(map);
        String i = orderInterface.insertOrder(map);
        return  i;
    }

    @PostMapping("/updatePayFlag")
    @ResponseBody
    public String updatePayFlag(@RequestBody int id){

        String i = orderInterface.updatePayFlag(id);
        return i;
    }

    @PostMapping("/updateSubmitFlag")
    @ResponseBody
    public String updateSubmitFlag(@RequestBody int id){

        String i = orderInterface.updateSubmitFlag(id);
        return  i;
    }

    @GetMapping("/deleteOrder")
    @ResponseBody
    public String deleteOrder(int id){

        String i = orderInterface.deleteOrder(id);
        return i;
    }


    @PostMapping("/getOrderByName")
    @ResponseBody
    public List<Order> getOrderByName(@RequestBody String name){
        return orderInterface.getOrderByName(name);
    }

    @PostMapping("/getOrderById")
    @ResponseBody
    public Order getOrderById(@RequestBody int id){
        return orderInterface.getOrderById(id);
    }


    @PostMapping("/getNoPayOrderByName")
    @ResponseBody
    public List<Order> getNoPayOrderByName(@RequestBody String name){
        return orderInterface.getNoPayOrderByName(name);
    }


}
