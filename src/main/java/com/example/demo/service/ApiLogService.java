package com.example.demo.service;

import com.example.demo.entity.ApiLog;

import java.util.List;

public interface ApiLogService {

    /**
     * 保存API日志
     * @param apiLog 日志对象
     * @return 是否保存成功
     */
    boolean saveLog(ApiLog apiLog);

    /**
     * 根据日志ID查询日志
     * @param logId 日志ID
     * @return 日志对象
     */
    ApiLog getLogById(String logId);

    /**
     * 获取最近的日志列表
     * @param limit 限制数量
     * @return 日志列表
     */
    List<ApiLog> getRecentLogs(int limit);
}

