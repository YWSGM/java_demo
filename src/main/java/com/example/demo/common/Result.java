package com.example.demo.common;

import java.io.Serializable;

/**
 * 通用返回结果类
 * @param <T> 数据类型
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 私有构造方法，通过静态方法创建实例
     */
    private Result() {}

    /**
     * 成功返回结果
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 成功返回结果
     * @param data 返回数据
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> success(T data) {
        return success(data, "操作成功");
    }

    /**
     * 成功返回结果
     * @param data 返回数据
     * @param message 返回消息
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> success(T data, String message) {
        return build(ResultCode.SUCCESS, message, data, true);
    }

    /**
     * 失败返回结果
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> failed() {
        return failed("操作失败");
    }

    /**
     * 失败返回结果
     * @param message 错误消息
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> failed(String message) {
        return build(ResultCode.FAILED, message, null, false);
    }

    /**
     * 失败返回结果
     * @param code 错误码
     * @param message 错误消息
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> failed(Integer code, String message) {
        return build(code, message, null, false);
    }

    /**
     * 参数验证失败返回结果
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> validateFailed() {
        return validateFailed("参数验证失败");
    }

    /**
     * 参数验证失败返回结果
     * @param message 错误消息
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> validateFailed(String message) {
        return build(ResultCode.VALIDATE_FAILED, message, null, false);
    }

    /**
     * 未登录返回结果
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> unauthorized() {
        return build(ResultCode.UNAUTHORIZED, "暂未登录或token已过期", null, false);
    }

    /**
     * 未授权返回结果
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> forbidden() {
        return build(ResultCode.FORBIDDEN, "没有相关权限", null, false);
    }

    /**
     * 资源不存在返回结果
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> notFound() {
        return build(ResultCode.NOT_FOUND, "资源不存在", null, false);
    }

    /**
     * 构建返回结果
     * @param code 状态码
     * @param message 返回消息
     * @param data 返回数据
     * @param success 是否成功
     * @param <T> 数据类型
     * @return Result对象
     */
    private static <T> Result<T> build(Integer code, String message, T data, Boolean success) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(success);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", success=" + success +
                '}';
    }
}

