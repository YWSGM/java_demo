package com.example.demo.filter;

import com.example.demo.utils.RequestWrapper;
import com.example.demo.utils.ResponseWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * API日志过滤器，用于包装请求和响应
 */
@Component
public class ApiLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 设置请求和响应的字符编码
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");

        // 包装请求和响应
        RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);

        try {
            // 继续过滤器链
            chain.doFilter(requestWrapper, responseWrapper);
        } finally {
            // 将响应内容写回原始响应
            responseWrapper.copyBodyToResponse();
        }
    }

    @Override
    public void destroy() {
        // 销毁过滤器
    }
}

