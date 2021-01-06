package com.jandy.grasszone.interceptor;

import com.jandy.grasszone.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SignInCheckInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("inter");
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO)session.getAttribute("user");
        if(userDTO == null)
        {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
