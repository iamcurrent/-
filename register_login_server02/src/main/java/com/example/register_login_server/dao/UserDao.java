package com.example.register_login_server.dao;


import com.example.commons.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {
    public User getUserByName(String name);
    public int insertUser(User user);
    public void updateUser(User user);
}
