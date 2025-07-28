package com.example.demo.mapper;

import com.example.demo.entity.ApiLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApiLogMapper {

    /**
     * 插入API日志
     * @param apiLog 日志对象
     * @return 影响的行数
     */
    int insert(ApiLog apiLog);

    /**
     * 根据日志ID查询日志
     * @param logId 日志ID
     * @return 日志对象
     */
    ApiLog findByLogId(@Param("logId") String logId);

    /**
     * 查询最近的日志列表
     * @param limit 限制数量
     * @return 日志列表
     */
    List<ApiLog> findRecentLogs(@Param("limit") int limit);
}

