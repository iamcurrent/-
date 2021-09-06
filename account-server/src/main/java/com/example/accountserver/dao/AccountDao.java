package com.example.accountserver.dao;

import com.example.commons.entities.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Mapper
@Component
public interface AccountDao {
    public int updateAccount(Map map);

    public Account getAccountByName(String name);

    public int insertAccount(Map map);

}
