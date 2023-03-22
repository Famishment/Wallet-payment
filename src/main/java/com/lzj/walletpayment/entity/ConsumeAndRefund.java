package com.lzj.walletpayment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * 支付/退款记录表
 */

@ApiModel(value = "消费/退款对象")
@Data
public class ConsumeAndRefund {

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String username;

    /**
     * 支付或退款金额
     */
    @ApiModelProperty(value = "支付或退款金额")
    private double money;

    /**
     * 支付或退款时间
     */
    @ApiModelProperty(value = "支付或退款时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

}
