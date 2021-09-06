package com.example.order_server.controllers;

import com.example.commons.entities.Order;
import com.example.order_server.services.OrderSqlServer;
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
public class OrderController {
    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    OrderSqlServer orderSqlServer;

    /**
     * "accountName":          accountName,
     *      *                 "tel":tel,
     *      *                 "date":date,
     *      *                 "money":money,
     *      *                 "savTime":savTime,
     *      *                 "storeName":storeName
     * @param map
     * @return
     */

    //插入订单
    @PostMapping("/submitOrder")
    @ResponseBody
    public String insertOrder(@RequestBody Map map){
        redisTemplate.opsForValue().setIfAbsent("orderIndex",0);
        Object orderIndex = redisTemplate.opsForValue().get("orderIndex");
        int i = (int)orderIndex;
        String accountName = (String) map.get("accountName");
        String tel =  (String) map.get("tel");
        String date=  (String)map.get("date");
        int money =  Integer.parseInt(map.get("money").toString());
        int savTime  =  Integer.parseInt(map.get("savTime").toString());
        String storeName  =  (String)map.get("storeName");
        String ig_address = (String)map.get("ig_address");
        Order order = new Order(i,accountName,tel,date,money,false,false,savTime,storeName,ig_address);
        int i1 = orderSqlServer.insertOrder(order);

        redisTemplate.opsForValue().increment("orderIndex");
        return String.valueOf(i); //返回订单编号
    }

    //修改订单的支付状态
    @PostMapping("/updatePayFlag")
    @ResponseBody
    public String updatePayFlag(@RequestBody int id){
        int i = orderSqlServer.updatePayFlag(id);
        return String.valueOf(i);
    }


    //修改物品的提交状态
    @PostMapping("/updateSubmitFlag")
    @ResponseBody
    public String updateSubmitFlag(@RequestBody int id){
        int i = orderSqlServer.updateSubmitFlag(id);
        return String.valueOf(i);
    }

    //通过id删除对应的订单
    @PostMapping("/deleteOrder")
    @ResponseBody
    public String deleteOrder(@RequestBody int id){
        int i = orderSqlServer.deleteOrder(id);
        return String.valueOf(i);
    }

    //通过用户的名字获取所有订单
    @PostMapping("/getOrderByName")
    @ResponseBody
    public List<Order> getOrderByName(@RequestBody String name){
        return orderSqlServer.getOrderByName(name);
    }

    //通过订单id,获取对应的订单
    @PostMapping("/getOrderById")
    @ResponseBody
    public Order getOrderById(@RequestBody int id){
        return orderSqlServer.getOrderById(id);
    }
    @PostMapping("/getNoPayOrderByName")
    @ResponseBody
    public List<Order> getNoPayOrderByName(@RequestBody String name){
        return orderSqlServer.getNoPayOrderByName(name);
    }

}
