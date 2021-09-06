package com.example.register_login_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RegisterLoginServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterLoginServerApplication.class, args);
    }

}
