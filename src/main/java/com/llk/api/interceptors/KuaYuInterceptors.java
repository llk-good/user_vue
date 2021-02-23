package com.llk.api.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KuaYuInterceptors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取跨域路径
        String origin = request.getHeader("Origin");
        //解决跨域 本质  设置response的相关信息  //设置允许跨域的资源
        response.addHeader("Access-Control-Allow-Origin", origin);
        //设置运行cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;

    }
}
