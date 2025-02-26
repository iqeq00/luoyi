package com.luoyi.example.order.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenlong
 */
@Data
public class Result<T> implements Serializable {

    private String code;

    private T data;

    private String msg;

    private Boolean success;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> failed() {
        return result("400", "调用失败，请检查。", null, false);
    }

    public static <T> Result<T> failed(String msg) {
        return result("400", msg, null, false);
    }

    public static <T> Result<T> judge(boolean status) {
        if (status) {
            return success();
        } else {
            return failed();
        }
    }

    public static <T> Result<T> failed(String code,String message) {
        return result(code, message, null, false);
    }


    private static <T> Result<T> result(String code, String msg, T data, boolean success) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        result.setSuccess(success);
        return result;
    }

    public static boolean isSuccess(Result<?> result) {
        return result != null && "0".equals(result.getCode());
    }
}
