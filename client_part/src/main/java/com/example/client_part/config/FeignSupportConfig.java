package com.example.client_part.config;

import com.example.client_part.entities.FeignBasicAuthRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignSupportConfig {

    /*@Bean
    public RequestInterceptor requestInterceptor(){

        return new FeignBasicAuthRequestInterceptor();
    }*/
}
