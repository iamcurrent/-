package com.example.client_part.Interface;

import com.example.client_part.entities.FeignBasicAuthRequestInterceptor;
import com.example.client_part.fallback.LoginAndRegisterFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(value = "login-server",fallbackFactory = LoginAndRegisterFallBack.class)
public interface LoginInterface {
    @PostMapping("/doLogin")
    public String doLogin(@RequestBody Map map);

    @PostMapping("/do_register")
    public String doRegister(@RequestBody Map map);

}
