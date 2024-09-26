package com.gtechsb.gtechfirstspringboot.components;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleFilter implements Filter {
    @Override
    public void destroy(){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

//        System.out.println("This is a Servlet doFilter() Method !");
//        System.out.println("Remote Host: " + request.getRemoteHost());
//        System.out.println("Remote Address: " + request.getRemoteAddr());
//        System.out.println("Remote Protocol: " + request.getProtocol());
//        System.out.println("getParameterNames: " + request.getParameterNames());
//        System.out.println("getContentType: " + request.getContentType());
//        System.out.println("getCharacterEncoding: " + request.getCharacterEncoding());
//        System.out.println("getLocalName: " + request.getLocalName());
        System.out.println("getRemoteAddr: " + httpServletRequest.getRemoteAddr());
        System.out.println("getRequestURI: " + httpServletRequest.getRequestURI());
        System.out.println("getMethod: " + httpServletRequest.getMethod());
        System.out.println("getQueryString: " + httpServletRequest.getQueryString());
        System.out.println("getRequestURL: " + httpServletRequest.getRequestURL());

        // Invoke filterChain to execute the next filter in order.
        filterchain.doFilter(httpServletRequest, httpServletResponse);
    }
    @Override
    public void init(FilterConfig filterconfig) throws ServletException {}
}
