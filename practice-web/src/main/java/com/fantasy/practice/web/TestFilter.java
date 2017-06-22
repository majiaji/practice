package com.fantasy.practice.web;


import javax.servlet.*;
import java.io.IOException;

/**
 * Created by jiaji on 2017/6/21.
 */
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.getParameter("haha");
    }

    @Override
    public void destroy() {

    }
}
