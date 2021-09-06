package com.example.client_part.fallback;

import com.example.client_part.Interface.LoginInterface;

import java.util.Map;

public class LoginAndRegisterFallBack implements LoginInterface {
    @Override
    public String doLogin(Map map) {
        return null;
    }

    @Override
    public String doRegister(Map map) {
        return null;
    }
}
