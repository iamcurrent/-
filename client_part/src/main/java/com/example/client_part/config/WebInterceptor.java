package com.example.client_part.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        Object token = request.getSession().getAttribute("token");
        if(token!=null&&!token.equals("null")){
            log.info("Interceptor:"+token);
            return true;
        }else {
            response.sendRedirect("/login_page");
            log.info("Interceptor:"+token);
            return false;
        }
    }
}
