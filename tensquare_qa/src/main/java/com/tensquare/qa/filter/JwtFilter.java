package com.tensquare.qa.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 创建拦截器类
 * @Author: WenChangSheng
 * @Date: Created in 2019/5/9 15:01
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("经过了拦截器");
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // The part after "Bearer "
            final String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                //如果是管理员
                if ("admin".equals(claims.get("roles"))) {
                    request.setAttribute("admin_claims", claims);
                }
                //如果是用户
                if ("user".equals(claims.get("roles"))) {
                    request.setAttribute("user_claims", claims);
                }
            }
        }
        return true;
    }
}
