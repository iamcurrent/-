package com.example.register_login_server.services;

import com.example.commons.entities.User;
import com.example.register_login_server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class UserServer {
    @Autowired
    UserDao userDao;

    public User getUserByName(String name){
        User userByName = userDao.getUserByName(name);
        return userByName;
    }
    public int insertUser(User user){
        int i = userDao.insertUser(user);
        return i;
    }
    public void updateUser(User user){
        userDao.updateUser(user);
    }


}
