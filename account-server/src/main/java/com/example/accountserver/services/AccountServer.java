package com.example.accountserver.services;

import com.example.accountserver.dao.AccountDao;
import com.example.commons.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountServer {
    @Autowired
    AccountDao accountDao;

    public int updateAccount(Map map){
        return accountDao.updateAccount(map);
    }

    public Account getAccountByName(String name){
        return accountDao.getAccountByName(name);
    }

    public int insertAccount(Map map){
        return accountDao.insertAccount(map);
    }

}
