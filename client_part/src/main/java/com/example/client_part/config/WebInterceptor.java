package com.example.client_part.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        Object token = request.getSession().getAttribute("token");
        if(getCookie(request.getCookies())||token!=null){
            System.out.println(requestURL);
            return true;
        }
        response.sendRedirect("/login_page");
        log.info("Interceptor:访问失败");
        return false;

    }

    private boolean getCookie(Cookie [] cookies){
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                return true;
            }
        }
        return false;
    }

}
