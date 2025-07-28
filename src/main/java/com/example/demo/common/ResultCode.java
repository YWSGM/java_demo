package com.example.demo.common;

/**
 * 返回状态码常量类
 */
public class ResultCode {
    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 失败
     */
    public static final int FAILED = 500;

    /**
     * 参数验证失败
     */
    public static final int VALIDATE_FAILED = 400;

    /**
     * 未认证
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 未授权
     */
    public static final int FORBIDDEN = 403;

    /**
     * 资源不存在
     */
    public static final int NOT_FOUND = 404;
}

