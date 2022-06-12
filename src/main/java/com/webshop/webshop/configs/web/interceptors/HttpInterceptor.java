package com.webshop.webshop.configs.web.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class HttpInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger("REQUEST");

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        logger.info("~> ["
                + new Date()
                + "] - REQUEST: "
                + request.getRequestURI() + " | "
                + request.getMethod());
        return true;
    }
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            ModelAndView modelAndView
    ) {
        logger.info("✅ [" + new Date() + "] - SUCCESSFUL: " +
                request.getRequestURI() + " " + request.getQueryString() +
                " | " + request.getMethod() + " | " + response.getStatus());
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            Exception exception
    ) {}

}
