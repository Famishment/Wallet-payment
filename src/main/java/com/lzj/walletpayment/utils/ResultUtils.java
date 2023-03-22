package com.lzj.walletpayment.utils;

/**
 * 通用返回工具类
 */

public class ResultUtils {

    public static <T>BaseResponse<T> success(T data){
        return new BaseResponse(200,data,"OK","请求成功");
    }

    public static <T>BaseResponse<T> error(T data){
        return new BaseResponse<>(400,null,"ERROR","请求失败");
    }

    public static BaseResponse error(StatusCode statusCode){
        return new BaseResponse(statusCode);
    }

    public static BaseResponse success(StatusCode statusCode){
        return new BaseResponse(statusCode);
    }

}
