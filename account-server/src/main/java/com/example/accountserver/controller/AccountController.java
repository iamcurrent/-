package com.example.accountserver.controller;

import com.example.accountserver.services.AccountServer;
import com.example.commons.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {
    @Autowired
    AccountServer accountServer;


    @PostMapping("/updateAccount")
    @ResponseBody
    public String updateAccount(@RequestBody Map map){
        //获取到对应的账户，对余额进行扣减
        System.out.println(map);
        String name = map.get("accountName").toString();
        int money = Integer.parseInt(map.get("money").toString());
        Account accountByName = accountServer.getAccountByName(name);
        if(accountByName==null){
            return "none";
        }
        int remainder = accountByName.getRemainder();
        if(remainder-money<0){
            return "n_enough";
        }

        Map map1 = new HashMap();
        map1.put("name",name);
        map1.put("money",remainder-money);
        int i = accountServer.updateAccount(map1);
        return "ok";
    }



    @PostMapping("/getAccountByName")
    @ResponseBody
    public Account getAccountByName(@RequestBody String name){
        Account accountByName = accountServer.getAccountByName(name);
        return accountByName;
    }


    @PostMapping("/insertAccount")
    @ResponseBody
    public String insertAccount(@RequestBody Map map){
        accountServer.insertAccount(map);
        return "ok";
    }

}
