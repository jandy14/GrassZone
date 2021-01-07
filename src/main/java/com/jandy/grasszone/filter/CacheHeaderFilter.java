package com.jandy.grasszone.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(
        urlPatterns = {"/board", "/post/*", "/comment/*"}
)
public class CacheHeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter activate");
        System.out.println(((HttpServletRequest)request).getRequestURL());
        ((HttpServletResponse)response).addHeader("Cache-Control", "no-store");
        chain.doFilter(request,response);

        // 왜 여기서 헤더를 추가하면 적용이 안되는 것인지
    }
}
