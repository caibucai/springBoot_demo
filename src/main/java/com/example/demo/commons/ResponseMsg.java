package com.example.demo.commons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;

import lombok.Data;
/**
 * @Description: 公用数据返回
 * @Author: crx
 * @Create: 15:54 2021/4/23
 */
@Data
public final class ResponseMsg<T> {
    private Integer code;
    private String message;
    private boolean success;
    private final T result;

    private ResponseMsg(Integer code, String message, boolean success, T result) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.result = result;
    }

    public static <T> ResponseMsg<T> forMsg(T result) {
        return forMsg(HttpStatus.SC_OK, true, result);
    }

    /**
     * @param code    状态码：200、500
     * @param success 成功标志位
     * @param result  数据
     * @param message 提示信息
     * @return ResponseMsg实例
     */
    public static <T> ResponseMsg<T> forMsg(Integer code, boolean success, T result, String message) {
        return new ResponseMsg(code, message, success, result);
    }

    /**
     * @param code    状态码
     * @param success 成功标志位
     * @param result  返回数据
     * @return ResponseMsg实例
     */
    public static <T> ResponseMsg<T> forMsg(Integer code, boolean success, T result) {
        return forMsg(code, success, result, null);
    }

    /**
     * 成功：code=200，失败：code=500
     *
     * @param success 成功标志位
     * @param result  数据
     * @return ResponseMsg实例
     */
    public static <T> ResponseMsg<T> forMsg(boolean success, T result) {
        Integer code = success ? HttpStatus.SC_OK : HttpStatus.SC_INTERNAL_SERVER_ERROR;
        return forMsg(code, success, result);
    }

    /**
     * 成功：code=200，失败：code=500
     *
     * @param {@link boolean}  success 成功标志位
     * @param obj    obj    数据
     * @return ResponseMsg实例
     */
    public static <T> ResponseMsg forMapMsg(boolean success, List<T> obj) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("list", obj);
        return forMsg(success, result);
    }

    /**
     * @param success 成功标志位
     * @return ResponseMsg实例
     */
    public static ResponseMsg<?> forMsg(boolean success) {
        return forMsg(success, null);
    }
}
