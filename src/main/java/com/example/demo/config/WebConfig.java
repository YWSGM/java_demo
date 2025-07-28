package com.example.demo.config;

import com.example.demo.interceptor.ApiLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApiLogInterceptor apiLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加API日志拦截器，拦截所有API请求
        registry.addInterceptor(apiLogInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/health"); // 排除健康检查接口
    }
}

