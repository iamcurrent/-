package com.example.client_part.fallback;

import com.example.client_part.Interface.AccountInterface;
import com.example.commons.entities.Account;

import java.util.Map;

public class AccountFallBack implements AccountInterface {


    @Override
    public String updateAccount(Map map) {
        return "404";
    }

    @Override
    public Account getAccountByName(String name) {
        return new Account();
    }

    @Override
    public String insertAccount(Map map) {
        return "404";
    }
}
