package com.example.client_part.Interface;

import com.example.client_part.fallback.AccountFallBack;
import com.example.commons.entities.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(value = "account-server",fallbackFactory = AccountFallBack.class)
public interface AccountInterface {
    @PostMapping("/updateAccount")
    public String updateAccount(@RequestBody Map map);

    @PostMapping("/getAccountByName")
    public Account getAccountByName(String name);

    @PostMapping("/insertAccount")
    @ResponseBody
    public String insertAccount(@RequestBody Map map);


}
