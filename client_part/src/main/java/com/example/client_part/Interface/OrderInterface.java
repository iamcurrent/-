package com.example.client_part.Interface;

import com.example.commons.entities.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@FeignClient("order-server")
public interface OrderInterface {

    @PostMapping("/submitOrder")
    public String insertOrder(@RequestBody Map map);

    @PostMapping("/updatePayFlag")
    public String updatePayFlag(@RequestBody int id);

    @PostMapping("/updateSubmitFlag")
    public String updateSubmitFlag(@RequestBody int id);

    @PostMapping("/deleteOrder")
    public String deleteOrder(@RequestBody int id);

    @PostMapping("/getOrderByName")
    public List<Order> getOrderByName(@RequestBody String name);

    @PostMapping("/getOrderById")
    public Order getOrderById(@RequestBody int id);

    @PostMapping("/getNoPayOrderByName")
    public List<Order> getNoPayOrderByName(@RequestBody String name);


}
