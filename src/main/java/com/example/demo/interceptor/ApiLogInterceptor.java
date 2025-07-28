package com.example.demo.interceptor;

import com.example.demo.entity.ApiLog;
import com.example.demo.service.ApiLogService;
import com.example.demo.utils.LogIdGenerator;
import com.example.demo.utils.RequestWrapper;
import com.example.demo.utils.ResponseWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * API日志拦截器
 */
@Component
public class ApiLogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ApiLogInterceptor.class);

    private static final String LOG_ID_HEADER = "X-Log-ID";
    private static final String REQUEST_START_TIME = "requestStartTime";

    @Autowired
    private ApiLogService apiLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置响应编码为UTF-8
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");

        // 生成日志ID
        String logId = LogIdGenerator.generateLogId();

        // 将日志ID添加到请求属性中，以便在整个请求过程中使用
        request.setAttribute(LOG_ID_HEADER, logId);

        // 记录请求开始时间
        request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());

        // 将日志ID添加到响应头中
        response.setHeader(LOG_ID_HEADER, logId);

        logger.debug("Request started with logId: {}", logId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在视图渲染之前执行，可以对ModelAndView进行修改
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            // 获取日志ID
            String logId = (String) request.getAttribute(LOG_ID_HEADER);

            // 获取请求开始时间
            Long startTime = (Long) request.getAttribute(REQUEST_START_TIME);
            Long executionTime = System.currentTimeMillis() - startTime;

            // 创建日志对象
            ApiLog apiLog = new ApiLog();
            apiLog.setLogId(logId);
            apiLog.setRequestUrl(request.getRequestURI());
            apiLog.setRequestMethod(request.getMethod());

            // 获取请求参数
            if (request instanceof RequestWrapper) {
                apiLog.setRequestParams(((RequestWrapper) request).getBodyString());
            } else {
                apiLog.setRequestParams(request.getQueryString());
            }

            // 获取响应数据
            if (response instanceof ResponseWrapper) {
                apiLog.setResponseData(((ResponseWrapper) response).getContentAsString());
            }

            // 设置状态码和错误信息
            apiLog.setStatusCode(response.getStatus());
            if (ex != null) {
                apiLog.setErrorMessage(ex.getMessage());
            }

            apiLog.setExecutionTime(executionTime);
            apiLog.setCreateTime(new Date());

            // 保存日志
            apiLogService.saveLog(apiLog);

            logger.debug("Request completed with logId: {}, execution time: {}ms", logId, executionTime);
        } catch (Exception e) {
            logger.error("Error saving API log", e);
        }
    }
}

