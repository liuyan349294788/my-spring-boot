package com.clockbone.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("请求 ：【{}】开始", uri);
        request.setAttribute("requestTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String uri = request.getRequestURI();
        if (ex != null) {
            log.error("请求 ：" + uri + " 异常，Exception：{}", ex);
        }
        String requestTime = String.valueOf(request.getAttribute("requestTime"));
        Long useTime = System.currentTimeMillis() - Long.parseLong(requestTime);
        log.info("请求 ：【{}】结束，耗时【{}】", uri, useTime);
        if (useTime > 5000) {
            log.info("请求：【{}】耗时超过5秒，实际耗时【{}】", uri, useTime);
        }
    }
}
