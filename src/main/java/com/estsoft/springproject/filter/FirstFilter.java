package com.estsoft.springproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

public class FirstFilter implements Filter {
    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("FirstFilter.init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FirstFilter.doFilter() request");

        // requestURI
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("requestURI: "+request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("FirstFilter.doFilter()");
    }

    @Override
    public void destroy() {
        System.out.println("FirstFilter destroy()");
    }
}
