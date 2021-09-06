package com.example.register_login_server.controllers;

import com.example.commons.entities.User;
import com.example.register_login_server.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    UserDao userDao;


    @PostMapping("/doLogin")
    @ResponseBody
    //@RequiresPermissions("visit")
    public String loginAuth(@RequestBody Map map){
        String username = (String) map.get("username");
        String password = (String)map.get("password");
        System.out.println("远方传来的信息："+username+password);
        User userByName = userDao.getUserByName(username);
        if(userByName==null){
            return "error";
        }else if(password.equals(userByName.getPassword())){
            return "ok";
        }else {
            return "error";
        }



        /*if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                password
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return "没有权限";
        }*/

    }

}
