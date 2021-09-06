package com.example.client_part.controllers;

import com.example.client_part.Interface.AccountInterface;
import com.example.client_part.Interface.OrderInterface;
import com.example.client_part.Interface.StorageInterface;
import com.example.commons.entities.Account;
import com.example.commons.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
@SuppressWarnings("all")
public class AccountImplController {
    @Autowired
    AccountInterface accountInterface;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    OrderInterface orderInterface;

    @Autowired
    StorageInterface storageInterface;

    //订单已经提交，现在开始支付
    @PostMapping("/updateAccount")
    @ResponseBody
    public String updateAccount(@RequestBody Map map){
        System.out.println(map);
        String order_index = orderInterface.updatePayFlag(Integer.parseInt(map.get("order_index").toString()));//修改订单为支付状态
        String s = accountInterface.updateAccount(map);//将账户扣除
        Map map1 = new HashMap();
        map1.put("shopName",map.get("shopName"));
        map1.put("code",-1);
        storageInterface.updateLeft(map1);//修改库存
        System.out.println(map);
        return s;
    }

    @PostMapping("/insertAccount")
    @ResponseBody
    public String insertAccount(@RequestBody Map map){
        String s = accountInterface.insertAccount(map);
        return "ok";
    }


    @GetMapping("/getAccountByName")
    @ResponseBody
    public Account getAccountByName(String name){
        System.out.println("用户信息:"+name);
        Account accountByName = accountInterface.getAccountByName(name);
        return accountByName;
    }

    @GetMapping("/accountPage")
    public String getAccountPage(HttpServletRequest request,Map map){
        Object token = request.getSession().getAttribute("token");
        System.out.println(token);
        //获取余额
        Account accountByName = accountInterface.getAccountByName(token.toString());
        System.out.println(accountByName);
        if(accountByName==null){
            map.put("left",-1);
            map.put("account",null);
        }else {
            map.put("left",accountByName.getRemainder());
            map.put("account",accountByName);
        }
        //获取消费记录
        List<Order> orderByName = orderInterface.getOrderByName(token.toString());
        map.put("order",orderByName);
        log.info("获取当前的账户信息");
        return "account_templates/index_account";
    }


}
