package com.example.register_login_server.controllers;


import com.example.commons.entities.User;
import com.example.register_login_server.services.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    UserServer userServer;

    @PostMapping("/do_register")
    @ResponseBody
    public String doRegister(@RequestBody Map map){
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        User userByName = userServer.getUserByName(username);
        if(userByName!=null){
            return "exist";
        }else {
            userServer.insertUser(new User(username, password));
            return "insert";
        }

    }
}
