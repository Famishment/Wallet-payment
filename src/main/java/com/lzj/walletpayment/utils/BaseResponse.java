package com.lzj.walletpayment.utils;

import lombok.Data;
import java.io.Serializable;

/**
 * 通用返回类
 * @param <T>
 */

@Data
public class BaseResponse<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回的数据
     */
    private T data; // 设置泛型，提高整个对象的可复用性

    /**
     * 状态码信息
     */
    private String message;

    /**
     * 状态码信息详情
     */
    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(StatusCode statusCode) {
        this(statusCode.getCode(), null, statusCode.getMessage(), statusCode.getDescription());
    }

}
