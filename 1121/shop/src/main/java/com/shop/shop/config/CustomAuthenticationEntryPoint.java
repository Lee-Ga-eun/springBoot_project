package com.shop.shop.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

        }
        else{
            response.sendRedirect("/member/login");
        }

        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"); --원래 이것만 있었음 if문 싹 다 없고



        //LOGGER.info("가입되지 않은 사용자 접근");
        //response.sendRedirect("/signin");
    }

}