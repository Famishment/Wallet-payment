package com.lzj.walletpayment.utils;


/**
 * 自定义错误码
 */
public enum StatusCode {

    SUCCESS(200,"OK","请求成功"),
    ERROR(400,"ERROR","请求失败");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码详情
     */
    private final String description;

    StatusCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
