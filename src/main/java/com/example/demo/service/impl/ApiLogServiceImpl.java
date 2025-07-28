package com.example.demo.service.impl;

import com.example.demo.entity.ApiLog;
import com.example.demo.mapper.ApiLogMapper;
import com.example.demo.service.ApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiLogServiceImpl implements ApiLogService {

    @Autowired
    private ApiLogMapper apiLogMapper;

    @Override
    public boolean saveLog(ApiLog apiLog) {
        return apiLogMapper.insert(apiLog) > 0;
    }

    @Override
    public ApiLog getLogById(String logId) {
        return apiLogMapper.findByLogId(logId);
    }

    @Override
    public List<ApiLog> getRecentLogs(int limit) {
        return apiLogMapper.findRecentLogs(limit);
    }
}

