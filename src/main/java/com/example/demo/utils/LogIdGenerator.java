package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 日志ID生成器
 */
public class LogIdGenerator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final AtomicInteger SEQUENCE = new AtomicInteger(0);
    private static final int MAX_SEQUENCE = 9999;

    /**
     * 生成日志ID
     * 格式：时间戳(yyyyMMddHHmmss) + 4位序列号 + 8位UUID
     * @return 日志ID
     */
    public static String generateLogId() {
        // 获取当前时间戳
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);

        // 获取序列号并确保不超过最大值
        int sequence = SEQUENCE.incrementAndGet();
        if (sequence > MAX_SEQUENCE) {
            SEQUENCE.set(0);
            sequence = SEQUENCE.incrementAndGet();
        }

        // 获取UUID的一部分
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);

        // 组合日志ID
        return timestamp + String.format("%04d", sequence) + uuid;
    }
}

